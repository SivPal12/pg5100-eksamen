package no.nith.sivpal12.pg5100.eksamen.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class ArtistDaoTest {
    private static final String ARTIST_NAME = "name";

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ArtistDaoTest.class);

    @Mock
    private EntityManager mockEntiryManager;
    @Mock
    private TypedQuery<Artist> mockTypedQuery;

    @InjectMocks
    private ArtistDao artistDao;

    @Before
    public void before() {
        when(mockEntiryManager.createNamedQuery(anyString(), eq(Artist.class)))
                .thenReturn(mockTypedQuery);
        when(mockTypedQuery.setParameter(anyInt(), anyString())).thenReturn(
                mockTypedQuery);
    }

    @Test
    public void allArtists_CallsEntiryManager() throws Exception {
        final List<Artist> expectedList = Collections.emptyList();
        when(mockTypedQuery.getResultList()).thenReturn(expectedList);

        assertEquals(expectedList, artistDao.allArtists());
    }

    @Test
    public void save_CallsEntityManager() {
        Artist artistToPersists = new Artist();

        artistDao.save(artistToPersists);

        verify(mockEntiryManager).persist(artistToPersists);
    }

    @Test
    public void getArtist_QueryByName_SetsParameter() {
        artistDao.getArtist(ARTIST_NAME);

        verify(mockEntiryManager).createNamedQuery(Artist.NAMED_QUERY_BY_NAME,
                Artist.class);
        verify(mockTypedQuery).setParameter(1, ARTIST_NAME);
    }

    @Test
    public void getArtist_ReturnsSingleResult() {
        final Artist artist = new Artist();

        when(mockTypedQuery.getSingleResult()).thenReturn(artist);

        assertEquals(artist, artistDao.getArtist(ARTIST_NAME));
    }
}
