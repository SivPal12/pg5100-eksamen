package no.nith.sivpal12.pg5100.eksamen.controllers.genre;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import no.nith.sivpal12.pg5100.eksamen.pojos.Genre;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class ReadGenreControllerTest {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ReadGenreControllerTest.class);

    @Mock
    private EntityManager mockEntityManager;

    @InjectMocks
    private ReadGenreController readGenreController;

    @Test
    public void getAll_CallsEntiryManager_ReturnsResult() {
        @SuppressWarnings("rawtypes")
        final TypedQuery mockTypedQuery = mock(TypedQuery.class);
        @SuppressWarnings("rawtypes")
        List genres = mock(List.class);

        when(mockTypedQuery.getResultList()).thenReturn(genres);
        when(
                mockEntityManager.createNamedQuery(Genre.NAMED_QUERY_ALL,
                        Genre.class))
                .thenReturn(mockTypedQuery);

        List<Genre> acturalGenres = readGenreController.getAll();

        verifyZeroInteractions(genres);
        assertEquals(genres, acturalGenres);
    }
}
