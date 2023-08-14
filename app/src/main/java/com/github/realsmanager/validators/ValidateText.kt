package com.github.realsmanager.validators

class ValidateText {
    fun execute(text: String): ValidationResult {
        if (text.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Field cannot be empty"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}