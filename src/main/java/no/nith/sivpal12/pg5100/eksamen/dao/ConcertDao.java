package no.nith.sivpal12.pg5100.eksamen.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
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

    @Produces
    @Named
    public List<Concert> allConcerts() {
        return entityManager.createNamedQuery(Concert.NAMED_QUERY_ALL,
                Concert.class).getResultList();
    }

    public void save(Concert concert) {
        entityManager.persist(concert);
    }

    public Concert find(int id) {
        return entityManager.find(Concert.class, id);
    }

    public void remove(int id) {
        entityManager.remove(find(id));
    }
}