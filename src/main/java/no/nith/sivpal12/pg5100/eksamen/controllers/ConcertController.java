package no.nith.sivpal12.pg5100.eksamen.controllers;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import no.nith.sivpal12.pg5100.eksamen.constraints.UniqueConcertName;
import no.nith.sivpal12.pg5100.eksamen.dao.ConcertDao;
import no.nith.sivpal12.pg5100.eksamen.pojos.Concert;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model
public class ConcertController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ConcertController.class);

    @Inject
    private ConcertDao concertDao;

    @Produces
    @Named
    private Concert concert;

    private int id;
    private Date from;
    private Date to;

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
        if (to != null) {
            return concertDao.concertsTo(to);
        }
        // TODO Refactor and implemented
        throw new RuntimeException("not yet implemented");
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @PostConstruct
    private void initFields() {
        concert = new Concert();
        uniqueConcertName = "";
        clearDates();
    }

}
