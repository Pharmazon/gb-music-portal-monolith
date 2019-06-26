package ru.geekbrains.musicportal.validation;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Настройка аннотации @ValidEmail
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {

    String message() default "Invalid email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
