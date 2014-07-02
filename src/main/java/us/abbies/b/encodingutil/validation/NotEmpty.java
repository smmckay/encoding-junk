package us.abbies.b.encodingutil.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {})
@ReportAsSingleViolation
@NotNull
@Size(min = 1)
@Retention(RUNTIME)
@Target({ANNOTATION_TYPE, METHOD, FIELD, CONSTRUCTOR, PARAMETER})
@Documented
public @interface NotEmpty {
    String message() default "{us.abbies.b.encodingutil.validation.NotEmpty.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
