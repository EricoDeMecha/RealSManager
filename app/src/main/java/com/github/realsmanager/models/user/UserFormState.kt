package com.github.realsmanager.models.user

data class UserFormState(
    val email: String = "",
    val emailError: String? = null,
    val name: String = "",
    val nameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null
)