package no.nith.sivpal12.pg5100.eksamen.controllers;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.nith.sivpal12.pg5100.eksamen.pojos.Genre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model
public class ArtistController {
    // OFF, ERROR, WARN, INFO, DEBUG, TRACE and ALL.
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ArtistController.class);

    @PersistenceContext(unitName = "myManager")
    private EntityManager entityManager;

    public List<Genre> getAllGenres() {
        LOGGER.trace("Fetching all genres from db");
        return entityManager.createNamedQuery(Genre.NAMED_QUERY_ALL,
                Genre.class).getResultList();
    }
}
