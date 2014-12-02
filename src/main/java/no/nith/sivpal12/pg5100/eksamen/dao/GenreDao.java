package no.nith.sivpal12.pg5100.eksamen.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.nith.sivpal12.pg5100.eksamen.maintenance.interceptors.LogMethodCalls;
import no.nith.sivpal12.pg5100.eksamen.pojos.Genre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@LogMethodCalls
public class GenreDao {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(GenreDao.class);

    @PersistenceContext(unitName = "myManager")
    private EntityManager entityManager;

    public List<Genre> getAllGenres() {
        return entityManager
                .createNamedQuery(Genre.NAMED_QUERY_ALL, Genre.class)
                .getResultList();
    }

    public Genre findByName(String genre) {
        return entityManager
                .createNamedQuery(Genre.NAMED_QUERY_ONE, Genre.class)
                .setParameter(1, genre)
                .getSingleResult();
    }
}
