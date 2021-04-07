package com.retromantis.apppeliculas.app


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.retromantis.apppeliculas.R
import com.retromantis.apppeliculas.databinding.ItemPeliculasBinding
import com.retromantis.apppeliculas.model.Result

class PeliculasAdapter(val peliculas:List<Result>):RecyclerView.Adapter<PeliculasAdapter.PeliculasHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasHolder {
        println(">>> onCreateViewHolder")
        val layoutInflater = LayoutInflater.from(parent.context)
        return PeliculasHolder(layoutInflater.inflate(R.layout.item_peliculas, parent, false))
    }

    override fun onBindViewHolder(holder: PeliculasHolder, position: Int) {
        println(">>> onBindViewHolder " + position)
        holder.render(peliculas[position])
    }

    override fun getItemCount(): Int = peliculas.size

    class PeliculasHolder(val view: View): RecyclerView.ViewHolder(view) {

        private val binding = ItemPeliculasBinding.bind(view)

        fun render(pelicula:Result) {
            binding.tvPelicula.text = pelicula.title
            println(">>> " + pelicula.title)
        }
    }

}