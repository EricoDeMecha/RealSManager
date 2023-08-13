package com.github.realsmanager

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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.realsmanager.components.COutlinedTextField
import com.github.realsmanager.components.CPasswordOutlinedField
import com.github.realsmanager.ui.theme.MainColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen() {
    var email by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirmedPassword by remember {
        mutableStateOf("")
    }
    var isSignUp by remember {
        mutableStateOf(false)
    }
    val isFormValid by remember {
        derivedStateOf {
            name.isNotBlank() && password.length >= 7
        }
    }
    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(MainColor),
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
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ),
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
                            fontSize = 32.sp
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        if (isSignUp) {
                            COutlinedTextField(
                                label = "email",
                                value = email,
                                onValueChange = {
                                    email = it
                                }
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        COutlinedTextField(
                            label = "name",
                            value = name,
                            onValueChange = {
                                name = it
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CPasswordOutlinedField(
                            label = "password",
                            value = password,
                            onValueChange = {
                                password = it
                            }
                        )
                        if (isSignUp) {
                            Spacer(modifier = Modifier.height(8.dp))
                            CPasswordOutlinedField(
                                label = "confirm password",
                                value = confirmedPassword,
                                onValueChange = {
                                    confirmedPassword = it
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {},
                            enabled = isFormValid,
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
                                 isSignUp  = !isSignUp
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