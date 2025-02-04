package com.nnk.springboot.config;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for validating passwords.
 * Ensures that passwords meet security constraints defined in {@link PasswordConstraintValidator}.
 */
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
	/**
     * Error message to be returned if validation fails.
     *
     * @return the error message
     */
    String message() default "Le mot de passe doit contenir au moins 8 caractères, une lettre majuscule, un chiffre et un symbole.";

    /**
     * Specifies validation groups.
     *
     * @return an array of validation groups
     */
    Class<?>[] groups() default {};

    /**
     * Specifies additional payload information.
     *
     * @return an array of payload classes
     */
    Class<? extends Payload>[] payload() default {};
}
