package us.abbies.b.encodingutil.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.Charset;

public class CanDecodeCharsetValidator implements ConstraintValidator<CanDecodeCharset, String> {
    @Override
    public void initialize(CanDecodeCharset constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Charset.forName(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
