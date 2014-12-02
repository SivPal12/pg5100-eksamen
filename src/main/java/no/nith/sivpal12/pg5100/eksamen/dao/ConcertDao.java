package no.nith.sivpal12.pg5100.eksamen.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.nith.sivpal12.pg5100.eksamen.pojos.Concert;
import no.nith.sivpal12.pg5100.eksamen.utils.DateUtils;

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
        LOGGER.trace("Getting all concerts");
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

    public List<Concert> concertsFrom(Date from) {
        LOGGER.trace(String.format("Getting concerts from '%s'", from));
        return entityManager
                .createNamedQuery(Concert.NAMED_QUERY_RANGE_FROM, Concert.class)
                .setParameter(1, from)
                .getResultList();
    }

    public List<Concert> concertsTo(Date to) {
        LOGGER.trace(String.format("Getting concerts to '%s'", to));
        return entityManager
                .createNamedQuery(Concert.NAMED_QUERY_RANGE_TO, Concert.class)
                .setParameter(1, DateUtils.addOneDay(to))
                .getResultList();
    }

    /**
     * @param from from date inclusive
     * @param to to date inclusive
     * @return List of dates within range.
     */
    public List<Concert> concerts(final Date from, final Date to) {
        final Date datePlusOne = DateUtils.addOneDay(to);
        LOGGER.trace(String
                .format("Getting concerts from '%s' to '%s'",
                        from, datePlusOne));
        return entityManager
                .createNamedQuery(Concert.NAMED_QUERY_RANGE_FULL, Concert.class)
                .setParameter(1, from)
                .setParameter(2, datePlusOne)
                .getResultList();
    }

    public List<Concert> getTopConcerts() {
        return entityManager
                .createNamedQuery(Concert.NAMED_QUERY_TOP_FIVE, Concert.class)
                .getResultList();
    }
}
