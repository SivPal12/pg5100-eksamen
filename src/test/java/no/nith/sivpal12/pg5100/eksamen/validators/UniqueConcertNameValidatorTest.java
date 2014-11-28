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
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import no.nith.sivpal12.pg5100.eksamen.pojos.Concert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UniqueConcertNameValidatorTest {

    private static final String CONCERT_NAME = "Name";
    @Mock
    private EntityManager mockEntityManager;
    @Mock
    private TypedQuery<Concert> mockTypedQuery;

    @InjectMocks
    private UniqueConcertNameValidator uniqueConcertNameValidator;

    @Before
    public void before() throws Exception {
        when(mockEntityManager.createNamedQuery(anyString(), eq(Concert.class)))
                .thenReturn(mockTypedQuery);
        when(mockTypedQuery.setParameter(anyInt(), anyString())).thenReturn(
                mockTypedQuery);
    }

    @Test
    public void isValid_QueryByName_SetsParameter() {
        uniqueConcertNameValidator.isValid(CONCERT_NAME, null);

        verify(mockEntityManager).createNamedQuery(Concert.NAMED_QUERY_BY_NAME,
                Concert.class);
        verify(mockTypedQuery).setParameter(1, CONCERT_NAME);
    }

    @Test
    public void isValid_EmptyList_ReturnsTrue() {
        assertTrue(uniqueConcertNameValidator.isValid(CONCERT_NAME, null));
    }

    @Test
    public void isValid_NonEmptyList_ReturnsFalse() {
        final List<Concert> list = new ArrayList<>();
        list.add(mock(Concert.class));

        when(mockTypedQuery.getResultList()).thenReturn(list);

        assertFalse(uniqueConcertNameValidator.isValid(CONCERT_NAME, null));
    }
}
