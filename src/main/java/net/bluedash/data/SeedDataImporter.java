package net.bluedash.data;

import net.bluedash.model.Member;
import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.transaction.UserTransaction;

/**
 * Import seed data into the database on application startup.
 * <p/>
 * <p>
 * Observes the context initialized event and loads seed data into the database using JPA.
 * </p>
 * <p/>
 * <p>
 * As an alternative, you can perform the data loading by observing the context initialized event of a ServletContextListener
 * </p>
 *
 * @author Dan Allen
 */
@Startup
@Singleton
public class SeedDataImporter {
    @Inject
    @Category("javaee")
    private Logger log;

    @Inject
    @MemberRepository
    private EntityManager em;

    @Inject
    private UserTransaction tx;

    @PostConstruct
    public void importData() {
        Member member1 = new Member();
        member1.setName("John Smith");
        member1.setEmail("john.smith@mailinator.com");
        member1.setPhoneNumber("2125551212");
        try {
            try {
                em.persist(member1);
            } catch (TransactionRequiredException e) {
                // manual transaction control required in @PostConstruct method
                // only use if enforced by JPA provider (due to bug in GlassFish)
                tx.begin();
                em.persist(member1);
                tx.commit();
            }
            log.info("Successfully imported seed data.");
        } catch (Exception e) {
            log.warn("Seed data import failed.", e);
        }

        // One To One with foreign key constrains.
        {
            net.bluedash.model.sandbox.onetoone.Address address
                    = new net.bluedash.model.sandbox.onetoone.Address();
            net.bluedash.model.sandbox.onetoone.User user
                    = new net.bluedash.model.sandbox.onetoone.User();
            user.setContactAddress(address);
            // http://stackoverflow.com/questions/11104897/hibernate-attempted-to-assign-id-from-null-one-to-one-property-employee
            // You told Hibernate to generate the ONETOONE_ADDRESS ID from the ONETOONE_USER ID, so you should initialize this property.
            address.setUser(user);
            em.persist(user);
            log.info("ONETOONE_USER ID: " + user.getId());
            log.info("ONETOONE_ADDRESS ID: " + address.getId());
        }
    }
}
