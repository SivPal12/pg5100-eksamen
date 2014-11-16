package no.nith.sivpal12.pg5100.eksamen.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import no.nith.sivpal12.pg5100.eksamen.validators.UniqueArtistNameValidator;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueArtistNameValidator.class)
@Documented
public @interface UniqueArtistName {

    String message() default "Artist name not unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
