package com.android.espressouitesting.data.source

import com.android.espressouitesting.data.Movie

interface MoviesDataSource {

    fun getMovie(movieId: Int): Movie?

    fun getMovies(): List<Movie>
}