package no.nith.sivpal12.pg5100.eksamen.controllers;

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
    private ConcertDao consertDao;

    @Produces
    @Named
    private Concert concert;

    private int id;

    @UniqueConcertName
    @NotEmpty
    @Pattern(regexp = ".*\\S+.*",
            message = "{org.hibernate.validator.constraints.NotEmpty.message}")
    private String uniqueConcertName;

    public void save() {
        concert.setName(uniqueConcertName);
        LOGGER.debug(String.format("Saving %s", concert));
        consertDao.save(concert);
        initFields();
    }

    public void load() {
        LOGGER.trace(String.format("Loading consert with id '%d'", getId()));
        concert = consertDao.find(getId());
    }

    public void remove(int id) {
        LOGGER.trace(String.format("Removing concert with id '%d'", id));
        consertDao.remove(id);
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

    @PostConstruct
    private void initFields() {
        concert = new Concert();
        uniqueConcertName = "";
        LOGGER.trace("Created new concert");
    }

}
