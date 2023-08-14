package com.github.realsmanager.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.realsmanager.R
import com.github.realsmanager.components.COutlinedTextField
import com.github.realsmanager.components.CPasswordOutlinedField
import com.github.realsmanager.models.UserFormEvent
import com.github.realsmanager.models.UserViewModel
import com.github.realsmanager.ui.theme.MainColor
import com.github.realsmanager.ui.theme.largeRadialGradient


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen() {
    var isSignUp by remember {
        mutableStateOf(false)
    }
    val userViewModel = viewModel<UserViewModel>()
    val userState = userViewModel.state
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        userViewModel.validationEvents.collect { event ->
            when (event) {
                is UserViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        if(isSignUp)"Registration successful" else "Login Successful",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(largeRadialGradient),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_reals_manager),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .size(200.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Card(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(32.dp),
                   /* colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ),*/
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = if (isSignUp) "Sign Up!" else "Welcome Back!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        if (isSignUp) {
                            COutlinedTextField(
                                label = "email",
                                value = userState.email,
                                onValueChange = {
                                    userViewModel.onEvent(UserFormEvent.EmailChanged(it))
                                },
                                isError = userState.emailError != null,
                                keyboardType = KeyboardType.Email
                            )
                            if (userState.emailError != null) {
                                Text(
                                    text = userState.emailError,
                                    color = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.align(Alignment.End)
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        COutlinedTextField(
                            label = "name",
                            value = userState.name,
                            onValueChange = {
                                userViewModel.onEvent(UserFormEvent.NameChanged(it))
                            },
                            isError = userState.nameError != null
                        )
                        if (userState.nameError != null) {
                            Text(
                                text = userState.nameError,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.align(Alignment.End)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        CPasswordOutlinedField(
                            label = "password",
                            value = userState.password,
                            onValueChange = {
                                userViewModel.onEvent(UserFormEvent.PasswordChanged(it))
                            },
                            isError = userState.passwordError != null
                        )
                        if (userState.passwordError != null) {
                            Text(
                                text = userState.passwordError,
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier.align(Alignment.End)
                            )
                        }
                        if (isSignUp) {
                            Spacer(modifier = Modifier.height(8.dp))
                            CPasswordOutlinedField(
                                label = "confirm password",
                                value = userState.confirmPassword,
                                onValueChange = {
                                    userViewModel.onEvent(UserFormEvent.ConfirmPasswordChanged(it))
                                },
                                isError = userState.confirmPasswordError != null
                            )
                            if (userState.confirmPasswordError != null) {
                                Text(
                                    text = userState.confirmPasswordError,
                                    color = MaterialTheme.colorScheme.error,
                                    modifier = Modifier.align(Alignment.End)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                if(isSignUp){
                                    userViewModel.onEvent(UserFormEvent.SignUpSubmit)
                                }else{
                                    userViewModel.onEvent(UserFormEvent.SignInSubmit)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(16.dp)),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        ) {
                            Text(
                                text = if (isSignUp) "Sign Up" else "Log In",
                                color = Color.White,
                                fontSize = 20.sp
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            TextButton(onClick = {
                                isSignUp = !isSignUp
                            }) {
                                Text(text = "Sign Up", fontSize = 16.sp)
                            }
                            TextButton(onClick = {}) {
                                Text(text = "Forgot Password?", color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
@Preview
fun SignInScreenPreview() {
    SignInScreen()
}