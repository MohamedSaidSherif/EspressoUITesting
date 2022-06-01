package com.android.espressouitesting.factory


import androidx.fragment.app.FragmentFactory
import com.android.espressouitesting.data.source.MoviesDataSource
import com.android.espressouitesting.ui.movie.DirectorsFragment
import com.android.espressouitesting.ui.movie.MovieDetailFragment
import com.android.espressouitesting.ui.movie.MovieListFragment
import com.android.espressouitesting.ui.movie.StarActorsFragment
import com.bumptech.glide.request.RequestOptions

class MovieFragmentFactory(
    private val requestOptions: RequestOptions? = null,
    private val moviesDataSource: MoviesDataSource? = null
) : FragmentFactory() {

    private val TAG: String = "MovieFragmentFactory"

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when (className) {
            MovieListFragment::class.java.name -> {
                if (moviesDataSource != null) {
                    MovieListFragment(
                        moviesDataSource
                    )
                } else {
                    super.instantiate(classLoader, className)
                }
            }

            MovieDetailFragment::class.java.name -> {
                if (requestOptions != null && moviesDataSource != null) {
                    MovieDetailFragment(
                        requestOptions,
                        moviesDataSource
                    )
                } else {
                    super.instantiate(classLoader, className)
                }
            }

            DirectorsFragment::class.java.name -> {
                DirectorsFragment()
            }

            StarActorsFragment::class.java.name -> {
                StarActorsFragment()
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }


}













