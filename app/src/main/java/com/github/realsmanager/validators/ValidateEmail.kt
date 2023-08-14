package com.github.realsmanager.validators

import android.util.Patterns

class ValidateEmail {
    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email cannot be blank"
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Email not valid"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}