package no.nith.sivpal12.pg5100.eksamen.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import no.nith.sivpal12.pg5100.eksamen.constraints.UniqueArtistName;
import no.nith.sivpal12.pg5100.eksamen.dao.ArtistDao;
import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model
public class ArtistController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ArtistController.class);

    @Inject
    private ArtistDao artistDao;

    @Named
    @Produces
    private Artist artist;

    @UniqueArtistName
    @NotEmpty
    @Pattern(regexp = ".*\\S+.*",
            message = "{org.hibernate.validator.constraints.NotEmpty.message}")
    private String uniqueArtistName;

    public List<Artist> getAllArtists() {
        return artistDao.allArtists();
    }

    public void save() {
        artist.setName(uniqueArtistName);
        LOGGER.trace(String.format("Saving %s", artist));
        artistDao.save(artist);
        initFields();
    }

    public String getUniqueArtistName() {
        return uniqueArtistName;
    }

    public void setUniqueArtistName(String uniqueArtistName) {
        this.uniqueArtistName = uniqueArtistName;
    }

    @PostConstruct
    private void initFields() {
        artist = new Artist();
        uniqueArtistName = "";
    }
}
