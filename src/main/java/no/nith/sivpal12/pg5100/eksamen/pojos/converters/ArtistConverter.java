package no.nith.sivpal12.pg5100.eksamen.pojos.converters;

import javax.ejb.Stateless;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import no.nith.sivpal12.pg5100.eksamen.dao.ArtistDao;
import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@FacesConverter(value = "artistConverter")
public class ArtistConverter implements Converter {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ArtistConverter.class);

    @Inject
    private ArtistDao artistDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        return artistDao.getArtist(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        return ((Artist) value).getName();
    }

}
