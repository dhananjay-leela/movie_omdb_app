package com.kermit.social.data

import com.kermit.social.BuildConfig
import com.kermit.social.data.model.MovieContentResponse
import com.kermit.social.data.model.SearchResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("/")
    fun getMovieByID(
        @Query("apikey") apiKey: String = BuildConfig.OMDB_API_KEY,
        @Query("i") imdbId: String,
    ) : Response<MovieContentResponse>

    @GET("/")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String = BuildConfig.OMDB_API_KEY,
        @Query("type") type: String = "movie",
        @Query("s") searchTerm: String,
        @Query("y") year: String,
        @Query("page") page: Int,
    ) : Response<SearchResponse>

    companion object {
        fun create(retrofit: Retrofit) = retrofit.create<MovieApiService>()
    }
}