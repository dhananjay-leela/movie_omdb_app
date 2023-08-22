package com.kermit.social.view.ui.screens


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.kermit.social.R
import com.kermit.social.view.MainViewModel
import com.kermit.social.view.ui.components.MovieGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSearchScreen(mainViewModel: MainViewModel) {

    val context = LocalContext.current
    val errorMessage by mainViewModel.errorMessage.observeAsState()

    var textInput by remember { mutableStateOf("Love") }
    val movieList by mainViewModel.movies.observeAsState()
    val isLoading by mainViewModel.isLoading.observeAsState(initial = false)


    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(16.dp)
            .fillMaxSize()
    ) {
        if (isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.8.toFloat()))
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = stringResource(id = R.string.app_name),
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Black
            )
            Button(onClick = { mainViewModel.loadMore() }
            ) {
                Text(text = "Load More")
            }
            Button(onClick = { mainViewModel.signOut() }) {
                Text("Sign Out")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            TextField(
                modifier = Modifier
                    .height(50.dp)
                    .weight(5f),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_placeholder),
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                },
                shape = RoundedCornerShape(24.dp),
                value = textInput,
                onValueChange = { textInput = it },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    containerColor = Color.White
                )
            )
            Button(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(50.dp)
                    .weight(1f)
                    .fillMaxWidth(),
                onClick = {
                    mainViewModel.fetchMovies(searchTerm = textInput)
                }
            ) {
                Image(
                    modifier = Modifier.clickable {
                        mainViewModel.fetchMovies(searchTerm = textInput)
                    },
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = ""
                )
            }
        }
        if (movieList != null) {
            MovieGrid(
                modifier = Modifier.padding(top = 24.dp),
                movieList = movieList!!
            )
        }
    }
}