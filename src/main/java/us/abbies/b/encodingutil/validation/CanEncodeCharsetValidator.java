package us.abbies.b.encodingutil.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.nio.charset.Charset;

public class CanEncodeCharsetValidator implements ConstraintValidator<CanEncodeCharset, String> {
    @Override
    public void initialize(CanEncodeCharset constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            return Charset.forName(value).canEncode();
        } catch (Exception e) {
            return false;
        }
    }
}
