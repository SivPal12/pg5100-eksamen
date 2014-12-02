package no.nith.sivpal12.pg5100.eksamen.session;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class SessionTest {

    private Session session;

    @Before
    public void before() throws Exception {
        session = new Session();
    }

    @Test
    public void getSet() {
        assertEquals(0, session.getCurrentConcertId());
        final int id = new Random().nextInt();
        session.setCurrentConcertId(id);
        assertEquals(id, session.getCurrentConcertId());
    }
}
