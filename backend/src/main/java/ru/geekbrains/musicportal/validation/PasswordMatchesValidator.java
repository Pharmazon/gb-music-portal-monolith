package ru.geekbrains.musicportal.validation;

import ru.geekbrains.musicportal.dto.UserRegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Валидатор длч пароля, в будущес можно усложнить.
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        UserRegistrationDto userRegistrationDto = (UserRegistrationDto) object;
        return userRegistrationDto.getPassword().equals(userRegistrationDto.getMatchingPassword());
    }
}
