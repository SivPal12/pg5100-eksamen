package no.nith.sivpal12.pg5100.eksamen.pojos;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConcertTest {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ConcertTest.class);

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(Concert.class)
                .suppress(Warning.STRICT_INHERITANCE).verify();
    }
}
