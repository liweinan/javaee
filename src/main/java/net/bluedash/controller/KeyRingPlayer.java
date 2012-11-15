package net.bluedash.controller;

import net.bluedash.data.MemberRepository;
import net.bluedash.model.sandbox.collection.orphan.Key;
import net.bluedash.model.sandbox.collection.orphan.KeyRing;
import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

/**
 * 11 15 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Named
@RequestScoped
public class KeyRingPlayer {

    @Inject
    @MemberRepository
    private EntityManager em;

    @Inject
    @Category("KEY-RING-PLAYER")
    private Logger log;

    @Inject
    private UserTransaction utx;

    public void tryDelete() throws Exception {
        utx.begin();
        KeyRing defaultRing = em.createQuery("from KeyRing", KeyRing.class).getSingleResult();
        Key orphan = defaultRing.getKeys().iterator().next();
        defaultRing.getKeys().remove(orphan);
        log.info(orphan.getColor() + " key is orphan now.");
        em.persist(defaultRing);
        utx.commit();

        utx.begin();
        KeyData.setKeys(em.createQuery("from Key").getResultList());
        utx.commit();
    }
}
