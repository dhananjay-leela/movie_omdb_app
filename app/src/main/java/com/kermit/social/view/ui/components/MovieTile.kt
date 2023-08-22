package com.kermit.social.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.kermit.social.R
import com.kermit.social.view.ui.theme.MovieSocialTheme
import java.util.Locale

@Composable
fun MovieTile(title: String, year: String, imageUrl: String) {
    Column {
        Box(modifier = Modifier
            .padding(6.dp)
            .aspectRatio(0.7f)
            .background(Color.Red)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_loading),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                fallback = painterResource(R.drawable.ic_image_not_found)
            )
        }
        Text(
            modifier = Modifier
                .padding(bottom = 4.dp)
                .align(Alignment.CenterHorizontally),
            text = title,
            fontSize = 14.sp, color = Color.White
        )
        Text(
            modifier = Modifier
                .padding(bottom = 4.dp)
                .align(Alignment.CenterHorizontally),
            text = year,
            fontSize = 14.sp, color = Color.White
        )
    }
}

@Composable
@Preview(name = "Movie Tile Preview")
private fun MovieTilePreview() {
    MovieSocialTheme {
//        MovieTile()
    }
}