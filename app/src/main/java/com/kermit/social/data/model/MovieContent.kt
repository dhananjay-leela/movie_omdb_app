package com.kermit.social.data.model

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("Title")
    val title: String,

    @SerializedName("Year")
    val year: String, // Using String for year because the year can be a range (e.g., "2019–2021")

    @SerializedName("imdbID")
    val imdbID: String,

    @SerializedName("Type")
    val type: String, // This could be an enum if you have fixed types like "movie" or "series"

    @SerializedName("Poster")
    val posterUrl: String
)

// Represents the entire search response
data class SearchResponse(
    @SerializedName("Search")
    val searchResults: List<MovieItem>,

    @SerializedName("totalResults")
    val totalResults: String, // Using String because the provided JSON has it as a String

    @SerializedName("Response")
    val isSuccessful: Boolean
)

data class MovieContentResponse (
    val title: String,
    val year: String,
    val rated: String,
    val released: String,
    val runtime: String,
    val genre: String,
    val director: String,
    val writer: String,
    val actors: String,
    val plot: String,
    val language: String,
    val country: String,
    val awards: String,
    val poster: String,
    val ratings: RatingsInfo,
    val metascore: String,
    val imdbRating: String,
    val imdbVotes: String,
    val imdbID: String,
    val type: String,
    val dvd: String,
    val boxOffice: String,
    val production: String,
    val website: String,
    val response: String,
)

data class RatingsInfo (
    val source: String,
    val value: String
)
