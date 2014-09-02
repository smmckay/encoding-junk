package us.abbies.b.encodingutil.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CanEncodeCharsetValidator.class)
@Retention(RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Documented
public @interface CanEncodeCharset {
    String message() default "Invalid charset";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
