package ru.kpfu.itis.validators.annotations;

import ru.kpfu.itis.validators.CustomEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Safin Ramil on 08.05.17
 */
@Target({TYPE, FIELD, ANNOTATION_TYPE, METHOD})
@Retention(RUNTIME)
@ReportAsSingleViolation
@Constraint(validatedBy = CustomEmailValidator.class)
@Documented
public @interface EmailValid {

    String message() default "Email is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}