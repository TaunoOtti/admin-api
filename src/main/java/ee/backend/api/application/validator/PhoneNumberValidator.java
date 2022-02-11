package ee.backend.api.application.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNo, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("(\\+\\d{1,3}?\\s?)?\\d{1,15}$");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
