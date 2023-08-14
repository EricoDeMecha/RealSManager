package com.github.realsmanager.validators

class ValidatePassword {
    fun execute(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password should be at least 8 characters"
            )
        }
        val containsLettersAndDigits =
            password.any() { it.isDigit() } && password.any { it.isLetter() }
        if (!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password should at least contain one letter or digit"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}