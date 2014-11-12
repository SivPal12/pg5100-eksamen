package no.nith.sivpal12.pg5100.eksamen.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import no.nith.sivpal12.pg5100.eksamen.dao.ArtistDao;
import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class ArtistControllerTest {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ArtistControllerTest.class);

    @Mock
    private ArtistDao mockArtistDao;

    @InjectMocks
    private ArtistController artistController;

    @Test
    public void getAllArtists_CallsDao_ReturnsList() throws Exception {
        List<Artist> expectedList = new ArrayList<>();

        when(mockArtistDao.allArtists()).thenReturn(expectedList);

        assertEquals(expectedList, artistController.getAllArtists());
    }
}
