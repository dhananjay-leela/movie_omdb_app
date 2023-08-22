package com.kermit.social.data

import com.kermit.social.data.model.SearchResponse

interface MovieSocialRepository {

    sealed class SearchMovieResponse {
        class Success(val content: SearchResponse) : SearchMovieResponse()

        class Error(val message: String = "Something Went wrong.") : SearchMovieResponse()
    }

    suspend fun getMovies(searchTerm: String, year: String, page: Int): SearchMovieResponse

    suspend fun getMoviesByImdbId(imdbId: Int): SearchMovieResponse
}