package com.kermit.social.data

import com.kermit.social.data.MovieSocialRepository.SearchMovieResponse
import java.lang.Exception

class KermitMovieSocialRepository(
    private val apiService: MovieApiService
) : MovieSocialRepository {
    override suspend fun getMovies(
        searchTerm: String,
        year: String,
        page: Int
    ): SearchMovieResponse {
        return try {
            val data = apiService.searchMovies(searchTerm = searchTerm, year = year,page = page).body()

            if (data != null) {
                SearchMovieResponse.Success(data)
            } else {
                SearchMovieResponse.Error()
            }
        } catch (e: Exception) {
            SearchMovieResponse.Error(e.message.toString())
        }
    }

    override suspend fun getMoviesByImdbId(imdbId: Int): SearchMovieResponse {
        TODO("Not yet implemented")
    }
}