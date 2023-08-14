package com.github.realsmanager.models

sealed class UserFormEvent {
    data class EmailChanged(val email: String) : UserFormEvent()
    data class PasswordChanged(val password: String) : UserFormEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : UserFormEvent()
    data class NameChanged(val name: String) : UserFormEvent()

    object SignInSubmit : UserFormEvent()
    object SignUpSubmit : UserFormEvent()
}
