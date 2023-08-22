package com.kermit.social.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kermit.social.view.ui.screens.MovieSearchScreen
import com.kermit.social.view.ui.screens.UserAuthenticationScreen
import com.kermit.social.view.ui.theme.MovieSocialTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieSocialTheme {
                NavGraph(viewModel = viewModel)
            }
        }
    }

    @Composable
    fun NavGraph(viewModel: MainViewModel) {
        val navController = rememberNavController()
        val isAuthenticated by viewModel.authenticated.observeAsState(initial = false)

        NavHost(
            navController,
            startDestination = if (isAuthenticated) "movieSearchScreen" else "userAuthenticationScreen"
        ) {
            composable("userAuthenticationScreen") {
                UserAuthenticationScreen(navController, viewModel)
            }
            composable("movieSearchScreen") {
                MovieSearchScreen(viewModel)
            }
        }
    }
}