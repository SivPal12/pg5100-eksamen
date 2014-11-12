package no.nith.sivpal12.pg5100.eksamen.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(MockitoJUnitRunner.class)
public class ArtistDaoTest {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ArtistDaoTest.class);

    @Mock
    private EntityManager mockEntiryManager;

    @InjectMocks
    private ArtistDao artistDao;

    @Test
    public void allArtists_CallsEntiryManager() throws Exception {
        final List<Artist> expectedList = new ArrayList<>();
        @SuppressWarnings("unchecked")
        TypedQuery<Artist> mockTypedQuery = mock(TypedQuery.class);

        when(mockTypedQuery.getResultList()).thenReturn(expectedList);
        when(
                mockEntiryManager.createNamedQuery(Artist.NAMED_QUERY_ALL,
                        Artist.class)).thenReturn(mockTypedQuery);

        assertEquals(expectedList, artistDao.allArtists());
    }
}
