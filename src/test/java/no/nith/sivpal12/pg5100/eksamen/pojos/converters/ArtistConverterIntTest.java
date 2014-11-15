package no.nith.sivpal12.pg5100.eksamen.pojos.converters;

import static org.junit.Assert.assertEquals;
import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;
import no.nith.sivpal12.pg5100.eksamen.pojos.Genre;
import no.nith.sivpal12.pg5100.eksamen.test.helpers.DbAndEntitySetupHelperForJUnit;

import org.junit.Test;

public class ArtistConverterIntTest extends DbAndEntitySetupHelperForJUnit {
    private static final String ARTIST_NAME = "Some name";

    @Test
    public void getAsObject_MultipleArtistsInDb_FindsCorrectByName() {
        entityManager.getTransaction().begin();
        Genre genre = entityManager
                .createNamedQuery(Genre.NAMED_QUERY_ONE, Genre.class)
                .setParameter(1, "pop").getSingleResult();
        entityManager.detach(genre);

        for (int i = 0; i < 10; i++) {
            Artist artist = new Artist();
            artist.setName("" + i);
            artist.setGenre(genre);

            entityManager.persist(artist);
        }

        final Artist expectedArtist = new Artist();
        expectedArtist.setName(ARTIST_NAME);
        expectedArtist.setGenre(genre);

        entityManager.persist(expectedArtist);
        entityManager.getTransaction().commit();

        assertEquals(
                expectedArtist,
                entityManager
                        .createNamedQuery(Artist.NAMED_QUERY_BY_NAME,
                                Artist.class)
                        .setParameter(1, ARTIST_NAME)
                        .getSingleResult());
    }
}
