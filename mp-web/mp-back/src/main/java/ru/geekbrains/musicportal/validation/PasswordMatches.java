package ru.geekbrains.musicportal.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Настройка аннотации @PasswordMatches
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {

    String message() default "Password don't match";

    Class<?>[] group() default {};

    Class<? extends Payload>[] payload() default {};

}
