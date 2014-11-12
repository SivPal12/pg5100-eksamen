package no.nith.sivpal12.pg5100.eksamen.controllers;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import no.nith.sivpal12.pg5100.eksamen.dao.ArtistDao;
import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model
public class ArtistController {
    // TODO merge to ArtistController
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ArtistController.class);

    @Inject
    private ArtistDao artistDao;

    public List<Artist> getAllArtists() {
        return artistDao.allArtists();
    }
}
