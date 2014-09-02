package us.abbies.b.encodingutil.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Pattern(regexp = "([A-Fa-f0-9][A-Fa-f0-9])+")
@Retention(RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Documented
public @interface HexBytes {
    String message() default "Invalid hex bytes";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
