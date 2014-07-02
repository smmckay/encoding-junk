package us.abbies.b.encodingutil.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {})
@ReportAsSingleViolation
@Pattern(regexp = "([A-Fa-f0-9][A-Fa-f0-9])+")
@NotNull
@Retention(RUNTIME)
@Target({ANNOTATION_TYPE, METHOD, FIELD, CONSTRUCTOR, PARAMETER})
@Documented
public @interface HexString {
    String message() default "{us.abbies.b.encodingutil.validation.HexString.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
