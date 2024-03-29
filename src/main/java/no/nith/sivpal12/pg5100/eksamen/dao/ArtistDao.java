package no.nith.sivpal12.pg5100.eksamen.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.nith.sivpal12.pg5100.eksamen.maintenance.interceptors.LogMethodCalls;
import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@LogMethodCalls
public class ArtistDao {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ArtistDao.class);

    @PersistenceContext(unitName = "myManager")
    private EntityManager entityManager;

    public List<Artist> allArtists() {
        return entityManager.createNamedQuery(Artist.NAMED_QUERY_ALL,
                Artist.class)
                .getResultList();
    }

    public void save(Artist artist) {
        entityManager.persist(artist);
    }

    public Artist getArtist(String artistName) {
        return entityManager
                .createNamedQuery(Artist.NAMED_QUERY_BY_NAME, Artist.class)
                .setParameter(1, artistName)
                .getSingleResult();
    }
}
