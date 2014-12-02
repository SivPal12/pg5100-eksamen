package no.nith.sivpal12.pg5100.eksamen.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.context.FacesContext;

import no.nith.sivpal12.pg5100.eksamen.dao.ConcertDao;
import no.nith.sivpal12.pg5100.eksamen.enums.ReserveTicketsResult;
import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;
import no.nith.sivpal12.pg5100.eksamen.pojos.Concert;
import no.nith.sivpal12.pg5100.eksamen.pojos.Genre;
import no.nith.sivpal12.pg5100.eksamen.services.TicketReserver;
import no.nith.sivpal12.pg5100.eksamen.session.Session;

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
    @Mock
    private Session mockSession;
    @Mock
    private TicketReserver mockTicketReserver;
    @Mock
    private FacesContext mockFacesContext;

    @InjectMocks
    private ConcertController concertController;

    private Concert validConcert;
    private List<Concert> listOfConcerts;

    @Before
    public void before() {
        validConcert = validConcert();
        listOfConcerts = new ArrayList<>();
        listOfConcerts.add(mock(Concert.class));
        listOfConcerts.add(mock(Concert.class));
        listOfConcerts.add(mock(Concert.class));
        listOfConcerts.add(mock(Concert.class));
    }

    @Test
    public void save_ValidConcert_CallsDao() {
        Whitebox.setInternalState(concertController, "concert", validConcert);

        concertController.save();

        verify(mockConcertDao).save(validConcert);
    }

    @Test
    public void load_CallsDaoWithId_NewConcertIsSet() {
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

    @Test
    public void filteredConcerts_FromToNull_CallsAllConcertsReturnsResult() {
        concertController.setFrom(null);
        concertController.setTo(null);

        when(mockConcertDao.allConcerts()).thenReturn(listOfConcerts);

        assertEquals(listOfConcerts, concertController.filteredConcerts());

        verify(mockConcertDao).allConcerts();
    }

    @Test
    public void filteredConcerts_FromNotNullToNotNull_CallsConcertsFromToReturnsResult() {
        Date from = new Date();
        Date to = new Date();
        concertController.setFrom(from);
        concertController.setTo(to);

        when(mockConcertDao.concerts(from, to)).thenReturn(listOfConcerts);

        assertEquals(listOfConcerts, concertController.filteredConcerts());

        verify(mockConcertDao).concerts(from, to);
    }

    @Test
    public void filteredConcerts_FromNullToNotNull_CallsConcertsToReturnsResult() {
        Date from = null;
        Date to = new Date();
        concertController.setFrom(from);
        concertController.setTo(to);

        when(mockConcertDao.concertsTo(to)).thenReturn(listOfConcerts);

        assertEquals(listOfConcerts, concertController.filteredConcerts());

        verify(mockConcertDao).concertsTo(to);
    }

    @Test
    public void filteredConcerts_FromNotNullToNull_CallsConcertsFromReturnsResult() {
        Date from = new Date();
        Date to = null;
        concertController.setFrom(from);
        concertController.setTo(to);

        when(mockConcertDao.concertsFrom(from)).thenReturn(listOfConcerts);

        assertEquals(listOfConcerts, concertController.filteredConcerts());

        verify(mockConcertDao).concertsFrom(from);
    }

    @Test
    public void doReserveTickets_TicketsReserved_SetsNewConcert() {
        final Concert expectedConcert = mock(Concert.class);
        final int id = new Random().nextInt(Integer.MAX_VALUE);

        concertController.setId(id);
        when(mockConcertDao.find(id)).thenReturn(expectedConcert);
        when(mockTicketReserver.reserveTickets(anyInt(), anyInt()))
                .thenReturn(ReserveTicketsResult.RESERVED);

        concertController.doReserveTickets();

        final Concert acturalConcert = (Concert) Whitebox
                .getInternalState(concertController, "concert");

        assertEquals(expectedConcert, acturalConcert);
    }

    @Test
    public void topConcerts_CallsDao_ReturnsResult() {
        when(mockConcertDao.getTopConcerts(anyInt()))
                .thenReturn(listOfConcerts);

        assertEquals(listOfConcerts, concertController.topFiveConcerts());
        verify(mockConcertDao).getTopConcerts(5);
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
        return concert;
    }
}
