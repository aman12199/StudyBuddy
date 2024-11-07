package com.example.studybuddy.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.studybuddy.R

@Composable
fun ForgotPassword(modifier: Modifier = Modifier,
                   navController: NavController,
                   viewModel: LoginViewModel
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    val resetPasswordState by viewModel.authState.observeAsState()



    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Forgot Password",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )


            Image(painter = painterResource(id = R.drawable.abb), contentDescription = "", modifier = Modifier.size(200.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Lost your password? Please enter your email address. You will receive a link to create a new password via mail")

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.resetPassword(email.text)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Send mail")
            }


            //   Display any errors or loading states
            when (resetPasswordState) {
                is AuthState.Success -> {
                    Snackbar { Text((resetPasswordState as AuthState.Success).message) }

                    // If reset is successful, navigate to the login screen
                    LaunchedEffect(Unit) {
                        navController.navigate("login") {
                            popUpTo("forgot_password") { inclusive = true }
                        }
                    }
                }
                is AuthState.Error -> {
                    Snackbar { Text((resetPasswordState as AuthState.Error).message) }
                }
                is AuthState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
                }
                else -> Unit
            }
        }
    }
}