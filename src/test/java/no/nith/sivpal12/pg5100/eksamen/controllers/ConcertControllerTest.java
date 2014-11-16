package no.nith.sivpal12.pg5100.eksamen.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Random;

import no.nith.sivpal12.pg5100.eksamen.dao.ConcertDao;
import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;
import no.nith.sivpal12.pg5100.eksamen.pojos.Concert;
import no.nith.sivpal12.pg5100.eksamen.pojos.Genre;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConcertControllerTest {

    @Mock
    private ConcertDao mockConcertDao;

    @InjectMocks
    private ConcertController concertController;

    private Concert validConcert;

    @Before
    public void before() {
        validConcert = validConcert();
    }

    @Test
    public void save_ValidConcert_CallsDao() {
        Whitebox.setInternalState(concertController, "concert", validConcert);

        concertController.save();

        verify(mockConcertDao).save(validConcert);
    }

    @Test
    public void load_CallsDAoWithId_NewConcertIsSet() {
        assertNull(Whitebox.getInternalState(concertController, "concert"));
        final int rand = new Random().nextInt();

        when(mockConcertDao.find(rand)).thenReturn(validConcert);
        concertController.setId(rand);

        concertController.load();

        final Concert actualConcert = (Concert) Whitebox.getInternalState(
                concertController, "concert");
        assertEquals(validConcert, actualConcert);
        verify(mockConcertDao).find(rand);
    }

    @Test
    public void remove_RandId_CallsDao() {
        final int randId = new Random().nextInt();

        concertController.remove(randId);

        verify(mockConcertDao).remove(randId);
    }

    private static Concert validConcert() {
        final Concert concert = new Concert();
        final Artist artist = new Artist();
        final Genre genre = new Genre();

        genre.setGenre("genre");

        artist.setGenre(genre);
        artist.setName("Artist name");

        concert.setArtist(artist);
        concert.setDate(new Date());
        concert.setDescription("Desc");
        concert.setLocation("Loc");
        concert.setNumTickets(9000);
        concert.setPrice(9001);
        concert.setTicketsSold(0);
        return null;
    }
}
