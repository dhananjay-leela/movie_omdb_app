package com.kermit.social.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kermit.social.data.AuthRepository
import com.kermit.social.data.MovieSocialRepository
import com.kermit.social.data.MovieSocialRepository.SearchMovieResponse.Error
import com.kermit.social.data.MovieSocialRepository.SearchMovieResponse.Success
import com.kermit.social.data.model.MovieItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch


class MainViewModel(
    private val ioDispatcher: CoroutineDispatcher,
    private val movieRepo: MovieSocialRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _authenticated = MutableLiveData(authRepository.isUserAuthenticated())
    val authenticated: LiveData<Boolean> = _authenticated

    private val _movies = MutableLiveData<List<MovieItem>>()
    val movies: LiveData<List<MovieItem>> = _movies

    private val _allMovies = MutableLiveData<List<MovieItem>>(emptyList())

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    val errorMessage = MutableLiveData<String>()

    private var currentPage = 1
    private var totalPages = Int.MAX_VALUE
    private var startYear: Int? = null
    private var currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)

    fun register(username: String, password: String): Boolean {
        return authRepository.register(username, password)
    }

    fun signIn(username: String, password: String) {
        _authenticated.postValue(authRepository.signIn(username, password))
    }

    fun signOut() {
        authRepository.signOut()
        _authenticated.postValue(false)
    }

    fun fetchMovies(searchTerm: String = "Love", startingYear: String = "2000") {
        setPageParams()
        startYear = startingYear.toInt()
        fetchNextPage(searchTerm)
    }

    fun loadMore(searchTerm: String = "Love") {
        fetchNextPage(searchTerm)
    }

    private fun fetchNextPage(searchTerm: String) {
        _isLoading.postValue(true)

        viewModelScope.launch(ioDispatcher) {
            if (currentPage > totalPages && startYear!! < currentYear) {
                startYear = startYear!! + 1
                setPageParams()
            }

            fetchAllPages(searchTerm, startYear.toString())
            _isLoading.postValue(false)

            val filteredMovies = _allMovies.value
                ?.filter { it.year.toInt() > 2000 }
                ?.sortedBy { it.year.toInt() }
            _movies.postValue(filteredMovies!!)
        }
    }

    private fun setPageParams() {
        currentPage = 1
        totalPages = Int.MAX_VALUE
    }

    private suspend fun fetchAllPages(searchTerm: String, year: String) {
        if (currentPage > totalPages || startYear!! > currentYear) return

        when (val response = movieRepo.getMovies(searchTerm = searchTerm, year = year, page = currentPage)) {
            is Success -> {
                response.content.searchResults?.let { currentMovies ->
                    val updatedMovies =
                        _allMovies.value.orEmpty().toMutableList().apply { addAll(currentMovies) }
                    _allMovies.postValue(updatedMovies)

                    response.content.totalResults.let { totalMovies ->
                        val totalResults = totalMovies.toInt()
                        totalPages = (totalResults + 9) / 10  // Calculate the total number of pages
                    }

                    currentPage++
                    fetchAllPages(searchTerm, year) // Recursive call to fetch the next page
                }
            }

            is Error -> {
                errorMessage.postValue(response.message)
            }
        }
    }
}