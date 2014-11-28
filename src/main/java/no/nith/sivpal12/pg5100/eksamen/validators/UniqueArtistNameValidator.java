package no.nith.sivpal12.pg5100.eksamen.validators;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import no.nith.sivpal12.pg5100.eksamen.constraints.UniqueArtistName;
import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class UniqueArtistNameValidator implements
        ConstraintValidator<UniqueArtistName, String> {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(UniqueConcertNameValidator.class);

    @PersistenceContext(unitName = "myManager")
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueArtistName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String artistName, ConstraintValidatorContext context) {
        boolean isValid = entityManager
                .createNamedQuery(Artist.NAMED_QUERY_BY_NAME, Artist.class)
                .setParameter(1, artistName)
                .getResultList().isEmpty();

        if (!isValid) {
            LOGGER.debug(String.format("Artist name '%s' is not unique!",
                    artistName));
        }

        return isValid;
    }
}
