package no.nith.sivpal12.pg5100.eksamen.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.nith.sivpal12.pg5100.eksamen.pojos.Concert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class ConcertDao  {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ConcertDao.class);

    @PersistenceContext(unitName = "myManager")
    private EntityManager entityManager;

    public void save(Concert concert) {
        entityManager.persist(concert);
    }
}
