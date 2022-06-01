package com.android.espressouitesting.factory


import androidx.fragment.app.FragmentFactory
import com.android.espressouitesting.ui.movie.DirectorsFragment
import com.android.espressouitesting.ui.movie.MovieDetailFragment
import com.android.espressouitesting.ui.movie.StarActorsFragment

class MovieFragmentFactory : FragmentFactory(){

    private val TAG: String = "MovieFragmentFactory"

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when(className){

            MovieDetailFragment::class.java.name -> {
                MovieDetailFragment()
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













