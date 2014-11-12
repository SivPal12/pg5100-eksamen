package no.nith.sivpal12.pg5100.eksamen.controllers.genre;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.nith.sivpal12.pg5100.eksamen.pojos.Genre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model
public class ReadGenreController {
    // TODO merge to GenreController
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ReadGenreController.class);

    @PersistenceContext(unitName = "myManager")
    private EntityManager entityManager;

    // TODO Create GenreDao
    public List<Genre> getAll() {
        LOGGER.trace("Getting all genres from db");
        return entityManager.createNamedQuery(Genre.NAMED_QUERY_ALL,
                Genre.class).getResultList();
    }
}
