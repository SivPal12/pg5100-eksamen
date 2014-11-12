package no.nith.sivpal12.pg5100.eksamen.controllers;

import javax.enterprise.inject.Model;

import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model
public class ConcertController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ConcertController.class);

    private Artist artist = new Artist();

    public void save() {
        // TODO Add date converter
        LOGGER.debug(String.format("Saving %s", artist));
        // TODO Auto-generated method stub
        throw new RuntimeException(String.format("'%s' not yet implemented",
                ConcertController.class.getName()));

    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
