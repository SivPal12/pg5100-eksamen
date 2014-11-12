package no.nith.sivpal12.pg5100.eksamen.controllers;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import no.nith.sivpal12.pg5100.eksamen.dao.GenreDao;
import no.nith.sivpal12.pg5100.eksamen.pojos.Genre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model
public class GenreController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenreController.class);

    @Inject
    private GenreDao genreDao;

    public List<Genre> getAllGenres() {
        LOGGER.trace("Getting all genres");
        return genreDao.getAllGenres();
    }
}
