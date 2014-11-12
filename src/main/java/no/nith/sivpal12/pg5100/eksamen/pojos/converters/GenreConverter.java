package no.nith.sivpal12.pg5100.eksamen.pojos.converters;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import no.nith.sivpal12.pg5100.eksamen.dao.GenreDao;
import no.nith.sivpal12.pg5100.eksamen.pojos.Genre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@FacesConverter(value = "genreConverter")
public class GenreConverter implements Converter {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(GenreConverter.class);

    @Inject
    private GenreDao genreDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        final List<Genre> allGenres = genreDao.getAllGenres();

        final Genre genre = new Genre();
        genre.setGenre(value);

        // TODO Implement not found
        return allGenres.get(allGenres.indexOf(genre));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        return ((Genre) value).getGenre();
    }

}
