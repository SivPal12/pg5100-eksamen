package no.nith.sivpal12.pg5100.eksamen.pojos.converters;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import no.nith.sivpal12.pg5100.eksamen.dao.ArtistDao;
import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ArtistConverterTest {

    private Artist artist;
    private static final String ARTIST_NAME = "Artist name";

    @Mock
    private ArtistDao mockArtistDao;

    @InjectMocks
    private ArtistConverter artistConverter;

    @Before
    public void before() throws Exception {
        artist = new Artist();
    }

    @Test
    public void getAsObject_CallsDao_ReturnsResult() {
        when(mockArtistDao.getArtist(ARTIST_NAME)).thenReturn(artist);

        assertEquals(artist,
                artistConverter.getAsObject(null, null, ARTIST_NAME));
    }

    @Test
    public void getAsString_ArtistObjectIn_ReturnsArtistName() {
        artist.setName(ARTIST_NAME);
        assertEquals(ARTIST_NAME,
                artistConverter.getAsString(null, null, artist));
    }
}
