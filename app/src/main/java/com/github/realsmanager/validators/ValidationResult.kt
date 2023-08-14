package com.github.realsmanager.validators

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String ?= null
)