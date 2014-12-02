package no.nith.sivpal12.pg5100.eksamen.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import no.nith.sivpal12.pg5100.eksamen.enums.ReserveTicketsResult;
import no.nith.sivpal12.pg5100.eksamen.pojos.Concert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TicketReserverTest {

    @Mock
    private EntityManager mockEntityManager;

    @InjectMocks
    private TicketReserver ticketReserver;

    private Concert concertTenFreeTickets;

    @Before
    public void before() throws Exception {
        concertTenFreeTickets = new Concert();
        concertTenFreeTickets.setNumTickets(10);
        concertTenFreeTickets.setTicketsSold(0);
        when(
                mockEntityManager.find(Concert.class,
                        concertTenFreeTickets.getId())).thenReturn(
                concertTenFreeTickets);
    }

    @Test
    public void reserveTickets_NotEnoughAvailableTickets_EnumNoTickets() {
        assertEquals(ReserveTicketsResult.NO_TICKETS_AVAILABLE,
                ticketReserver.reserveTickets(concertTenFreeTickets.getId(), 11));
    }

    @Test
    public void reserveTickets_AvailableTickets_EnumReserved() {
        assertEquals(0, concertTenFreeTickets.getTicketsSold());
        assertEquals(ReserveTicketsResult.RESERVED,
                ticketReserver.reserveTickets(concertTenFreeTickets.getId(), 10));
        assertEquals(10, concertTenFreeTickets.getTicketsSold());
    }
}
