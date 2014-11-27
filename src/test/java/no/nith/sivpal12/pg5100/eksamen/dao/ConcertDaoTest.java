package no.nith.sivpal12.pg5100.eksamen.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;
import no.nith.sivpal12.pg5100.eksamen.pojos.Concert;
import no.nith.sivpal12.pg5100.eksamen.pojos.Genre;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConcertDaoTest {

    @Mock
    private EntityManager mockEntityManager;

    @InjectMocks
    private ConcertDao concertDao;

    @Test
    public void save_ValidConcert_ConcertFoundInEntityManager() {
        final Concert concert = validConcert();
        concertDao.save(concert);

        verify(mockEntityManager).persist(concert);
    }

    @Test
    public void allConcerts_CallsEntityManager_ReturnsAllItems() {
        @SuppressWarnings("unchecked")
        final TypedQuery<Concert> mockTypedQuery = mock(TypedQuery.class);
        List<Concert> expectedList = new ArrayList<>();

        when(mockTypedQuery.getResultList()).thenReturn(expectedList);
        when(
                mockEntityManager.createNamedQuery(Concert.NAMED_QUERY_ALL,
                        Concert.class)).thenReturn(mockTypedQuery);

        assertEquals(expectedList, concertDao.allConcerts());
    }

    @Test
    public void find_RandId_CallsEntityManagerWithId() {
        final int randId = new Random().nextInt();
        final Concert expectedConcert = validConcert();

        when(mockEntityManager.find(Concert.class, randId)).thenReturn(
                expectedConcert);

        final Concert acturalConcert = concertDao.find(randId);

        assertEquals(expectedConcert, acturalConcert);
        verify(mockEntityManager).find(Concert.class, randId);
    }

    @Test
    public void remove_RandId_CallsEntityManagerWithResultFromFind() {
        final int randId = new Random().nextInt();
        final Concert concert = validConcert();
        when(mockEntityManager.find(Concert.class, randId)).thenReturn(concert);

        concertDao.remove(randId);

        verify(mockEntityManager).remove(concert);
    }

    @Test
    public void concerts_ValidDates_AddsOneDayAndCallsEntityManager() {
        @SuppressWarnings("unchecked")
        final TypedQuery<Concert> mockTypedQuery = mock(TypedQuery.class);
        final List<Concert> expectedList = new ArrayList<>();
        expectedList.add(mock(Concert.class));
        expectedList.add(mock(Concert.class));
        expectedList.add(mock(Concert.class));

        when(mockTypedQuery.getResultList()).thenReturn(expectedList);
        when(mockTypedQuery.setParameter(anyInt(), any(Date.class)))
                .thenReturn(mockTypedQuery);
        when(
                mockEntityManager.createNamedQuery(
                        Concert.NAMED_QUERY_RANGE_FULL, Concert.class))
                .thenReturn(mockTypedQuery);

        final Date to = new Date();
        final Date toDatePlusOneDay = new Date(to.getTime()
                + TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
        assertEquals(expectedList, concertDao.concerts(new Date(), to));

        verify(mockTypedQuery).setParameter(2, toDatePlusOneDay);
    }

    @Test
    public void concertsFrom_ValidDate_ReturnsExpectedList() {
        @SuppressWarnings("unchecked")
        final TypedQuery<Concert> mockTypedQuery = mock(TypedQuery.class);
        List<Concert> expectedList = new ArrayList<>();
        expectedList.add(mock(Concert.class));
        expectedList.add(mock(Concert.class));
        expectedList.add(mock(Concert.class));

        when(mockTypedQuery.getResultList()).thenReturn(expectedList);
        when(mockTypedQuery.setParameter(anyInt(), any(Date.class)))
                .thenReturn(mockTypedQuery);
        when(mockEntityManager.createNamedQuery(anyString(), eq(Concert.class)))
                .thenReturn(mockTypedQuery);

        assertEquals(expectedList, concertDao.concertsFrom(new Date()));

        verify(mockEntityManager).createNamedQuery(
                Concert.NAMED_QUERY_RANGE_FROM, Concert.class);
    }

    @Test
    public void concertsTo_ValidDate_AddsOneDayAndReturnsExpectedList() {
        @SuppressWarnings("unchecked")
        final TypedQuery<Concert> mockTypedQuery = mock(TypedQuery.class);
        List<Concert> expectedList = new ArrayList<>();
        expectedList.add(mock(Concert.class));
        expectedList.add(mock(Concert.class));
        expectedList.add(mock(Concert.class));

        when(mockTypedQuery.getResultList()).thenReturn(expectedList);
        when(mockTypedQuery.setParameter(anyInt(), any(Date.class)))
                .thenReturn(mockTypedQuery);
        when(mockEntityManager.createNamedQuery(anyString(), eq(Concert.class)))
                .thenReturn(mockTypedQuery);

        assertEquals(expectedList, concertDao.concertsTo(new Date(0)));

        verify(mockTypedQuery).setParameter(1,
                new Date(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)));
        verify(mockEntityManager).createNamedQuery(
                Concert.NAMED_QUERY_RANGE_TO, Concert.class);
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
