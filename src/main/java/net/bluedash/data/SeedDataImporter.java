package net.bluedash.data;

import net.bluedash.model.Member;
import net.bluedash.model.sandbox.collection.bag.Item;
import net.bluedash.model.sandbox.collection.embed.Color;
import net.bluedash.model.sandbox.collection.embed.Pencil;
import net.bluedash.model.sandbox.collection.orphan.Key;
import net.bluedash.model.sandbox.collection.orphan.KeyRing;
import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

        // collection.bag
        {
            Item item = new Item();
            Collection<String> links = new ArrayList<String>();
            links.add("linka");
            links.add("linkb");
            links.add("linkc");
            links.add("linkc");

            item.setLinks(links);

            em.persist(item);
        }

        // collection.embed
        {
            Pencil pencil = new Pencil();

            Color purple = new Color();
            purple.setName("Purple");
            purple.setAbbreviation("PR");

            Color red = new Color();
            red.setName("Red");
            red.setAbbreviation("RD");

            Color red2 = new Color();
            red2.setName("Red");
            red2.setAbbreviation("RD");

            Set<Color> colors = new HashSet<Color>();
            colors.add(purple);
            colors.add(red);

            // colors.add(red2); will cause problem, because
            // (pencil_id, name, abbreviation) is used as composite id.
            pencil.setColors(colors);

            em.persist(pencil);
        }

        // Generate Orphan enabled entities
        {
            KeyRing ring = new KeyRing();

            Key key1 = new Key();
            key1.setColor("Red");
            Key key2 = new Key();
            key2.setColor("Blue");

            ring.setName("default");
            ring.addKey(key1);
            ring.addKey(key2);

            em.persist(ring);
        }
    }
}
