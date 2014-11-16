package no.nith.sivpal12.pg5100.eksamen.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
public class GenreDaoTest {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(GenreDaoTest.class);

    @Mock
    private EntityManager mockEntityManager;

    @InjectMocks
    private GenreDao genreDao;

    @Test
    public void getAllGenres_CallsEntityManager_ReturnsAllItems() {
        @SuppressWarnings("unchecked")
        final TypedQuery<Genre> mockTypedQuery = mock(TypedQuery.class);
        List<Genre> expectedList = new ArrayList<>();

        when(mockTypedQuery.getResultList()).thenReturn(expectedList);
        when(
                mockEntityManager.createNamedQuery(Genre.NAMED_QUERY_ALL,
                        Genre.class)).thenReturn(mockTypedQuery);

        assertEquals(expectedList, genreDao.getAllGenres());
    }

    @Test
    public void findByName_ValidGenre_CallsEntityManagerAndReturnsResult() {
        final String genreName = "genre";
        final Genre genre = new Genre();
        genre.setGenre(genreName);
        @SuppressWarnings("unchecked")
        final TypedQuery<Genre> mockTypedQuery = mock(TypedQuery.class);

        when(
                mockEntityManager.createNamedQuery(Genre.NAMED_QUERY_ONE,
                        Genre.class)).thenReturn(mockTypedQuery);
        when(mockTypedQuery.setParameter(1, genreName)).thenReturn(
                mockTypedQuery);
        when(mockTypedQuery.getSingleResult()).thenReturn(genre);

        assertEquals(genre, genreDao.findByName(genreName));
    }
}
