package no.nith.sivpal12.pg5100.eksamen.controllers.artist;

import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;
import no.nith.sivpal12.pg5100.eksamen.pojos.Genre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model
@Stateful
public class CreateArtistController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(CreateArtistController.class);

    @NotNull
    @Pattern(regexp = ".+")
    private String name = "";

    @NotNull
    @Pattern(regexp = ".+")
    private String genre = "";
    @PersistenceContext(unitName = "myManager")
    private EntityManager entityManager;

    public void save() {
        Genre actualGenre;
        try {
            actualGenre = entityManager
                    .createNamedQuery(Genre.NAMED_QUERY_ONE, Genre.class)
                    .setParameter(1, genre)
                    .getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            LOGGER.warn(String
                    .format("Db contains more than or less than one genre of type '%s'",
                            genre));
            // TODO Handle unknown genre
            throw new RuntimeException("not yet implemented");
        }

        final Artist artist = new Artist.Builder()
                .setName(name)
                .setGenre(actualGenre)
                .build();
        LOGGER.trace(String.format("Saving {%s}", artist));
        entityManager.persist(artist);
    }

    public String getName() {
        LOGGER.trace("Read name");
        return name;
    }

    public void setName(String name) {
        LOGGER.trace("Set name");
        this.name = name;
    }

    public String getGenre() {
        LOGGER.trace("Read genre");
        return genre;
    }

    public void setGenre(String genre) {
        LOGGER.trace("Set genre");
        this.genre = genre;
    }
}
