package com.android.espressouitesting.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.espressouitesting.R
import com.android.espressouitesting.data.source.MoviesDataSource
import com.android.espressouitesting.data.source.MoviesRemoteDataSource
import com.android.espressouitesting.factory.MovieFragmentFactory
import com.bumptech.glide.request.RequestOptions

class MoviesActivity : AppCompatActivity() {

    //dependencies (typically would be injected with dagger)
    lateinit var requestOptions: RequestOptions
    lateinit var moviesDataSource: MoviesDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        initDependencies()
        supportFragmentManager.fragmentFactory = MovieFragmentFactory(
            requestOptions,
            moviesDataSource
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        init()
    }

    private fun init() {
        if (supportFragmentManager.fragments.size == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieListFragment::class.java, null)
                .commit()
        }
    }

    private fun initDependencies(){
        if(!::requestOptions.isInitialized){
            // glide
            requestOptions = RequestOptions
                .placeholderOf(R.drawable.default_image)
                .error(R.drawable.default_image)
        }
        if(!::moviesDataSource.isInitialized){
            // Data Source
            moviesDataSource = MoviesRemoteDataSource()
        }
    }
}