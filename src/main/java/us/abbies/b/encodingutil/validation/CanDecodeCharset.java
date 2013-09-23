package us.abbies.b.encodingutil.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CanDecodeCharsetValidator.class)
@Retention(RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
public @interface CanDecodeCharset {
    String message() default "{us.abbies.b.encodingutil.validation.CanDecodeCharset.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
