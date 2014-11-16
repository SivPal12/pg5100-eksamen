package no.nith.sivpal12.pg5100.eksamen.pojos.converters;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;

import no.nith.sivpal12.pg5100.eksamen.dao.GenreDao;
import no.nith.sivpal12.pg5100.eksamen.pojos.Genre;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GenreConverterTest {

    @Mock
    private GenreDao mockGenreDao;

    @InjectMocks
    private GenreConverter genreConverter;

    @Test
    public void getAsObject_RandString_ReturnsGenre() {
        final String rand = String.valueOf(new Random().nextInt(50));
        final Genre expectedGenre = new Genre();
        expectedGenre.setGenre(rand);

        when(mockGenreDao.findByName(rand)).thenReturn(expectedGenre);

        assertEquals(expectedGenre,
                genreConverter.getAsObject(null, null, rand));
        verify(mockGenreDao).findByName(rand);
    }

    @Test
    public void getAsString_RandGenreString_ReturnsString() {
        final String rand = String.valueOf(new Random().nextInt(50));
        final Genre genre = new Genre();
        genre.setGenre(rand);

        assertEquals(rand, genreConverter.getAsString(null, null, genre));
    }
}
