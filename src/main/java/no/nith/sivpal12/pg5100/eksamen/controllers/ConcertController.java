package no.nith.sivpal12.pg5100.eksamen.controllers;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import no.nith.sivpal12.pg5100.eksamen.constraints.UniqueConcertName;
import no.nith.sivpal12.pg5100.eksamen.dao.ConcertDao;
import no.nith.sivpal12.pg5100.eksamen.enums.ReserveTicketsResult;
import no.nith.sivpal12.pg5100.eksamen.pojos.Concert;
import no.nith.sivpal12.pg5100.eksamen.services.TicketReserver;
import no.nith.sivpal12.pg5100.eksamen.session.Session;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model
public class ConcertController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ConcertController.class);

    @Inject
    private ConcertDao concertDao;
    @Inject
    private TicketReserver ticketReserver;
    @Inject
    private Session session;
    @Inject
    private FacesContext facesContext;

    @Produces
    @Named
    private Concert concert;

    private int id;
    private Date from;
    private Date to;
    @Min(value = 1)
    private int numTicketsToReserve;

    @UniqueConcertName
    @NotEmpty
    @Pattern(regexp = ".*\\S+.*",
            message = "{org.hibernate.validator.constraints.NotEmpty.message}")
    private String uniqueConcertName;

    public void clearDates() {
        from = null;
        to = null;
    }

    @Named
    @Produces
    public List<Concert> filteredConcerts() {
        LOGGER.trace(String.format("Getting concerts from %s to %s", from, to));
        if (from == null && to == null) {
            return concertDao.allConcerts();
        }
        if (from != null && to != null) {
            return concertDao.concerts(from, to);
        }
        if (from != null) {
            return concertDao.concertsFrom(from);
        }
        return concertDao.concertsTo(to);
    }

    @Named
    @Produces
    public List<Concert> topFiveConcerts() {
        return concertDao.getTopConcerts(5);
    }

    public void save() {
        concert.setName(uniqueConcertName);
        LOGGER.debug(String.format("Saving %s", concert));
        concertDao.save(concert);
        initFields();
    }

    public void load() {
        LOGGER.trace(String.format("Loading consert with id '%d'", getId()));
        concert = concertDao.find(getId());
    }

    public void remove(int id) {
        LOGGER.trace(String.format("Removing concert with id '%d'", id));
        concertDao.remove(id);
    }

    public void doReserveTickets() {
        LOGGER.trace(String.format("Reserving %d tickets",
                getNumTicketsToReserve()));

        ReserveTicketsResult result = ticketReserver
                .reserveTickets(id, numTicketsToReserve);

        // TODO Find out how to test
        switch (result) {
            case RESERVED:
                setViewMessage(String.format("Reserved %d tickets.",
                        numTicketsToReserve));
                concert = concertDao.find(id);
                break;

            case NO_TICKETS_AVAILABLE:
                setViewMessage("No tickets avaliable");
                break;

            default:
                LOGGER.error(String
                        .format("Enum '%s' not implemented!", result.name()));
                setViewMessage("Unknown error");
        }
    }

    private void setViewMessage(String message) {
        facesContext.addMessage(null, new FacesMessage(message));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        session.setCurrentConcertId(id);
    }

    public String getUniqueConcertName() {
        return uniqueConcertName;
    }

    public void setUniqueConcertName(String uniqueConcertName) {
        this.uniqueConcertName = uniqueConcertName;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        LOGGER.trace(String.format("Set from: %s", from));
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        LOGGER.trace(String.format("Set to: %s", to));
        this.to = to;
    }

    public int getNumTicketsToReserve() {
        return numTicketsToReserve;
    }

    public void setNumTicketsToReserve(int numTicketsToReserve) {
        this.numTicketsToReserve = numTicketsToReserve;
    }

    @PostConstruct
    private void initFields() {
        if (id <= 0) {
            id = session.getCurrentConcertId();
        }
        concert = concertDao.find(id);
        uniqueConcertName = "";
        clearDates();
    }

}
