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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConcertDaoTest {

    @Mock
    private EntityManager mockEntityManager;
    @Mock
    private TypedQuery<Concert> mockTypedQuery;

    @InjectMocks
    private ConcertDao concertDao;

    private List<Concert> uniqueConcertList;

    @Before
    public void before() throws Exception {
        uniqueConcertList = new ArrayList<>();
        uniqueConcertList.add(mock(Concert.class));
        uniqueConcertList.add(mock(Concert.class));
        uniqueConcertList.add(mock(Concert.class));

        when(mockEntityManager.createNamedQuery(anyString(), eq(Concert.class)))
                .thenReturn(mockTypedQuery);
        when(mockTypedQuery.setParameter(anyInt(), any()))
                .thenReturn(mockTypedQuery);
    }

    @Test
    public void save_ValidConcert_ConcertFoundInEntityManager() {
        final Concert concert = validConcert();
        concertDao.save(concert);

        verify(mockEntityManager).persist(concert);
    }

    @Test
    public void allConcerts_CallsEntityManager_ReturnsAllItems() {
        List<Concert> expectedList = new ArrayList<>();

        when(mockTypedQuery.getResultList()).thenReturn(expectedList);

        assertEquals(expectedList, concertDao.allConcerts());
        verify(mockEntityManager).createNamedQuery(Concert.NAMED_QUERY_ALL,
                Concert.class);
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
        when(mockTypedQuery.getResultList()).thenReturn(uniqueConcertList);

        final Date to = new Date();
        final Date toDatePlusOneDay = new Date(to.getTime()
                + TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
        assertEquals(uniqueConcertList, concertDao.concerts(new Date(), to));

        verify(mockTypedQuery).setParameter(2, toDatePlusOneDay);
        verify(mockEntityManager).createNamedQuery(
                Concert.NAMED_QUERY_RANGE_FULL, Concert.class);
    }

    @Test
    public void concertsFrom_ValidDate_ReturnsExpectedList() {
        when(mockTypedQuery.getResultList()).thenReturn(uniqueConcertList);

        assertEquals(uniqueConcertList, concertDao.concertsFrom(new Date()));

        verify(mockEntityManager).createNamedQuery(
                Concert.NAMED_QUERY_RANGE_FROM, Concert.class);
    }

    @Test
    public void concertsTo_ValidDate_AddsOneDayAndReturnsExpectedList() {
        when(mockTypedQuery.getResultList()).thenReturn(uniqueConcertList);

        assertEquals(uniqueConcertList, concertDao.concertsTo(new Date(0)));

        verify(mockTypedQuery).setParameter(1,
                new Date(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)));
        verify(mockEntityManager).createNamedQuery(
                Concert.NAMED_QUERY_RANGE_TO, Concert.class);
    }

    @Test
    public void getTopConcerts_CallsEntityManager_ReturnsResult() {
        final int numberOfConcerts = 5;

        when(mockTypedQuery.getResultList()).thenReturn(uniqueConcertList);
        when(mockTypedQuery.setMaxResults(anyInt())).thenReturn(mockTypedQuery);

        assertEquals(uniqueConcertList,
                concertDao.getTopConcerts(numberOfConcerts));

        verify(mockEntityManager)
                .createNamedQuery(Concert.NAMED_QUERY_ORDER_BY_TOP_CONCERTS, Concert.class);
        verify(mockTypedQuery).setMaxResults(numberOfConcerts);
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
