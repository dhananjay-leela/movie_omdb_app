package com.kermit.social.view.ui.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kermit.social.data.model.MovieItem

@Composable
fun MovieGrid(
    modifier: Modifier = Modifier,
    movieList: List<MovieItem>
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        content = {
            items(movieList.size) {
                MovieTile(
                    imageUrl = movieList[it].posterUrl,
                    title = movieList[it].title,
                    year = movieList[it].year
                )
            }
        }
    )
}

