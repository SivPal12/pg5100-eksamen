package no.nith.sivpal12.pg5100.eksamen.services;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.nith.sivpal12.pg5100.eksamen.enums.ReserveTicketsResult;
import no.nith.sivpal12.pg5100.eksamen.pojos.Concert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class TicketReserver {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(TicketReserver.class);

    @PersistenceContext(unitName = "myManager")
    private EntityManager entityManager;

    public ReserveTicketsResult reserveTickets(int concertId,
            int ticketsToReserve) {
        final Concert concert = entityManager.find(Concert.class, concertId);

        if (concert.getNumTickets() >= concert.getTicketsSold()
                + ticketsToReserve) {
            concert.setTicketsSold(concert.getTicketsSold() + ticketsToReserve);
            return ReserveTicketsResult.RESERVED;
        }
        return ReserveTicketsResult.NO_TICKETS_AVAILABLE;
    }
}
