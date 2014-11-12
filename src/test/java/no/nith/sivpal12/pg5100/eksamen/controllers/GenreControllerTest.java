package no.nith.sivpal12.pg5100.eksamen.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import no.nith.sivpal12.pg5100.eksamen.dao.GenreDao;
import no.nith.sivpal12.pg5100.eksamen.pojos.Genre;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class GenreControllerTest {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(GenreControllerTest.class);

    @Mock
    private GenreDao mockGenreDao;

    @InjectMocks
    private GenreController genreController;

    @Test
    public void getAllGenres_CallsDao_ReturnsList() {
        final List<Genre> expectedList = new ArrayList<>();

        when(mockGenreDao.getAllGenres()).thenReturn(expectedList);

        assertEquals(expectedList, genreController.getAllGenres());

        verify(mockGenreDao).getAllGenres();
    }

}
