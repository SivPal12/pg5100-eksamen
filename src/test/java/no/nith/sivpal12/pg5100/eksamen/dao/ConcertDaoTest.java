package no.nith.sivpal12.pg5100.eksamen.dao;

import static org.mockito.Mockito.verify;

import java.util.Date;

import javax.persistence.EntityManager;

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
