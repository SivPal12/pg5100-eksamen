package no.nith.sivpal12.pg5100.eksamen.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
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
}
