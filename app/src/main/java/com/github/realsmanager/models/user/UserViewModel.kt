package com.github.realsmanager.models.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.realsmanager.validators.ValidateConfirmPassword
import com.github.realsmanager.validators.ValidateEmail
import com.github.realsmanager.validators.ValidatePassword
import com.github.realsmanager.validators.ValidateText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validateName: ValidateText = ValidateText(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateConfirmPassword: ValidateConfirmPassword = ValidateConfirmPassword()
) : ViewModel() {
    var state by mutableStateOf(UserFormState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: UserFormEvent) {
        when (event) {
            is UserFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is UserFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is UserFormEvent.ConfirmPasswordChanged -> {
                state = state.copy(confirmPassword = event.confirmPassword)
            }

            is UserFormEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }

            is UserFormEvent.SignInSubmit -> {
                submitSignInData()
            }

            is UserFormEvent.SignUpSubmit -> {
                submitSignUpData()
            }

        }
    }

    private fun submitSignInData() {
        val nameResult = validateName.execute(state.name)
        val passwordResult = validatePassword.execute(state.password)

        val hasError = listOf(
            nameResult,
            passwordResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                nameError = nameResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    private fun submitSignUpData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val confirmPasswordResult =
            validateConfirmPassword.execute(state.password, state.confirmPassword)
        val nameResult = validateName.execute(state.name)

        val hasError = listOf(
            emailResult,
            passwordResult,
            nameResult,
            confirmPasswordResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                confirmPasswordError = confirmPasswordResult.errorMessage,
                nameError = nameResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}