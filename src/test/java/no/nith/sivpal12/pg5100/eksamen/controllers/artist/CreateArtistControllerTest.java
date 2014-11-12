package no.nith.sivpal12.pg5100.eksamen.controllers.artist;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;
import no.nith.sivpal12.pg5100.eksamen.pojos.Genre;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class CreateArtistControllerTest {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(CreateArtistControllerTest.class);

    @Mock
    private EntityManager mockEntityManager;

    @InjectMocks
    private CreateArtistController createArtistController;

    @Test
    public void save_GenreInDb_PersistsNewArtistWithGenre() {
        final String genre = "Rock";
        final String name = "Buckcerry";

        Whitebox.setInternalState(createArtistController, "genre", genre);
        Whitebox.setInternalState(createArtistController, "name", name);

        @SuppressWarnings("unchecked")
        final TypedQuery<Genre> mockTypedQuery = mock(TypedQuery.class);
        final Genre mockGenre = mock(Genre.class);
        final ArgumentCaptor<Artist> captorArtist = ArgumentCaptor
                .forClass(Artist.class);

        when(mockTypedQuery.getSingleResult()).thenReturn(mockGenre);
        when(mockTypedQuery.setParameter(1, genre)).thenReturn(mockTypedQuery);
        when(mockEntityManager.createNamedQuery(Genre.NAMED_QUERY_ONE, Genre.class))
                .thenReturn(mockTypedQuery);

        createArtistController.save();

        verify(mockEntityManager).persist(captorArtist.capture());
        assertEquals(mockGenre, captorArtist.getValue().getGenre());
        assertEquals(name, captorArtist.getValue().getName());
    }
}
