package no.nith.sivpal12.pg5100.eksamen.validators;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import no.nith.sivpal12.pg5100.eksamen.constraints.UniqueArtistName;
import no.nith.sivpal12.pg5100.eksamen.pojos.Artist;

@Stateless
public class UniqueArtistNameValidator implements
        ConstraintValidator<UniqueArtistName, String> {

    @PersistenceContext(unitName = "myManager")
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueArtistName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String artistName, ConstraintValidatorContext context) {
        return entityManager
                .createNamedQuery(Artist.NAMED_QUERY_BY_NAME, Artist.class)
                .setParameter(1, artistName)
                .getResultList().isEmpty();
    }
}
