package com.retromantis.apppeliculas.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.retromantis.apppeliculas.app.App
import com.retromantis.apppeliculas.databinding.ActivityDetallesBinding
import com.squareup.picasso.Picasso

class DetallesActivity: AppCompatActivity() {

    private lateinit var binding:ActivityDetallesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + App.pelicula.poster_path).into(binding.ivPoster);
        binding.tvTitle.text = App.pelicula.title
        binding.tvVoteAverage.text = "Nota: " + App.pelicula.vote_average.toString()
        binding.tvReleaseDate.text = "Fecha: " + App.pelicula.release_date
        binding.tvOverview.text = App.pelicula.overview
    }
}