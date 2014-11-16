package no.nith.sivpal12.pg5100.eksamen.validators;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import no.nith.sivpal12.pg5100.eksamen.constraints.UniqueConcertName;
import no.nith.sivpal12.pg5100.eksamen.pojos.Concert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class UniqueConcertNameValidator implements
        ConstraintValidator<UniqueConcertName, String> {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(UniqueConcertNameValidator.class);

    @PersistenceContext(unitName = "myManager")
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueConcertName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String concertName,
            ConstraintValidatorContext context) {
        return entityManager
                .createNamedQuery(Concert.NAMED_QUERY_BY_NAME, Concert.class)
                .setParameter(1, concertName)
                .getResultList().isEmpty();
    }
}
