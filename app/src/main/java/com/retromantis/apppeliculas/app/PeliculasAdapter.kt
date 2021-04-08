package com.retromantis.apppeliculas.app


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.retromantis.apppeliculas.R
import com.retromantis.apppeliculas.databinding.ItemPeliculasBinding
import com.retromantis.apppeliculas.model.Pelicula

class PeliculasAdapter(val peliculas:List<Pelicula>):RecyclerView.Adapter<PeliculasAdapter.PeliculasHolder>(), View.OnClickListener {

    private lateinit var listener: View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view:View = layoutInflater.inflate(R.layout.item_peliculas, parent, false)
        view.setOnClickListener(this)
        return PeliculasHolder(view)
    }

    override fun onBindViewHolder(holder: PeliculasHolder, position: Int) {
        holder.render(peliculas[position])
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int = peliculas.size

    override fun onClick(view: View?) {
        if(listener!=null) {
            listener.onClick(view)
        }
    }

    class PeliculasHolder(val view: View): RecyclerView.ViewHolder(view) {

        private val binding = ItemPeliculasBinding.bind(view)

        fun render(pelicula:Pelicula) {
            binding.tvPelicula.text = pelicula.title
        }
    }

}