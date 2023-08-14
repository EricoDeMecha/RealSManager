package com.github.realsmanager.validators

class ValidateConfirmPassword {
    fun execute(password: String, confirmPassword: String): ValidationResult{
        if(password != confirmPassword){
            return ValidationResult(
                successful = false,
                errorMessage = "Passwords do not match"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}