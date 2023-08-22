package com.kermit.social.view.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import com.kermit.social.view.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserAuthenticationScreen(navController: NavController, viewModel: MainViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = username, onValueChange = { username = it }, label = { Text("Username") })
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(onClick = {
            if (viewModel.register(username, password)) {
                viewModel.signIn(username, password)
                if (viewModel.authenticated.value == true) {
                    navController.navigate("movieSearch")
                } else {
                    Toast.makeText(context, "Authentication Failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "User already exists", Toast.LENGTH_SHORT).show()
            }
        }) { Text("Sign Up") }

        Button(onClick = {
            viewModel.signIn(username, password)
            if (viewModel.authenticated.value == true) {
                navController.navigate("movieSearch")
            } else {
                Toast.makeText(context, "Authentication Failed", Toast.LENGTH_SHORT).show()
            }
        }) { Text("Sign In") }
    }
}