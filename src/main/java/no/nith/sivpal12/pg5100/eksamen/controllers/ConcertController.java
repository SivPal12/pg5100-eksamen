package no.nith.sivpal12.pg5100.eksamen.controllers;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import no.nith.sivpal12.pg5100.eksamen.dao.ConcertDao;
import no.nith.sivpal12.pg5100.eksamen.pojos.Concert;

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

    public void save() {
        // TODO Add date converter
        LOGGER.debug(String.format("Saving %s", concert));
        consertDao.save(concert);
        initConcert();
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

    @PostConstruct
    private void initConcert() {
        concert = new Concert();
        LOGGER.trace("Created new concert");
    }

}