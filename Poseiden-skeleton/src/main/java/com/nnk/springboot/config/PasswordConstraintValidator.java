package com.nnk.springboot.config;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator for password constraints using the {@link ValidPassword} annotation.
 * Ensures that the password meets the required security criteria:
 * - At least 8 characters long
 * - Contains at least one uppercase letter
 * - Contains at least one digit
 * - Contains at least one special character
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

	/** Regular expression pattern for password validation. */
    private static final String PASSWORD_PATTERN =
            "^(?=.*[A-Z])(?=.*[0-9])(?=.*[\\W_]).{8,}$";

    /**
     * Initializes the validator. No specific initialization required.
     *
     * @param constraintAnnotation the annotation instance
     */
    @Override
    public void initialize(ValidPassword constraintAnnotation) {}

    /**
     * Validates the given password against the defined pattern.
     *
     * @param password the password to validate
     * @param context context for the validation process
     * @return {@code true} if the password matches the pattern, {@code false} otherwise
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }
        return password.matches(PASSWORD_PATTERN);
    }
}

