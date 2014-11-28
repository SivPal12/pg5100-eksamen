package no.nith.sivpal12.pg5100.eksamen.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

@RunWith(MockitoJUnitRunner.class)
public class UniqueArtistNameValidatorTest {

    @Mock
    private EntityManager mockEntityManager;
    @Mock
    private TypedQuery<Artist> mockTypedQuery;

    @InjectMocks
    private UniqueArtistNameValidator uniqueArtistNameValidator;

    private static final String ARTIST_NAME = "Name";

    @Before
    public void before() {
        when(mockEntityManager.createNamedQuery(anyString(), eq(Artist.class)))
                .thenReturn(mockTypedQuery);
        when(mockTypedQuery.setParameter(anyInt(), anyString())).thenReturn(mockTypedQuery);
    }

    @Test
    public void isValid_QueryByName_SetsPArameter() {
        uniqueArtistNameValidator.isValid(ARTIST_NAME, null);

        verify(mockEntityManager).createNamedQuery(Artist.NAMED_QUERY_BY_NAME,
                Artist.class);
        verify(mockTypedQuery).setParameter(1, ARTIST_NAME);
    }

    @Test
    public void isValid_ResultListIsEmpty_ReturnsTrue() {
        when(mockTypedQuery.getResultList()).thenReturn(
                Collections.<Artist> emptyList());
        assertTrue(uniqueArtistNameValidator.isValid(ARTIST_NAME, null));
    }

    @Test
    public void isValid_ResultListHasElements_ReturnsFalse() {
        final List<Artist> list = new ArrayList<>();
        list.add(mock(Artist.class));
        when(mockTypedQuery.getResultList()).thenReturn(
                list);
        assertFalse(uniqueArtistNameValidator.isValid(ARTIST_NAME, null));
    }

}
