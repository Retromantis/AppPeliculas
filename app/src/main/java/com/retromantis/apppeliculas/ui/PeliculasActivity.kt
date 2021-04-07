package com.retromantis.apppeliculas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.retromantis.apppeliculas.app.APIService
import com.retromantis.apppeliculas.app.PeliculasAdapter
import com.retromantis.apppeliculas.databinding.ActivityPeliculasBinding
import com.retromantis.apppeliculas.model.PeliculasResponse
import com.retromantis.apppeliculas.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PeliculasActivity : AppCompatActivity() {

    private val BASE_URL = "https://api.themoviedb.org/3/movie/"
    private val API_KEY  = "ec52c6bb69d5e9dddab334efe8f45e96"

    private lateinit var binding:ActivityPeliculasBinding
    private lateinit var apiService: APIService
    private lateinit var adapter: PeliculasAdapter
    private val peliculas = mutableListOf<Result>()

    private var pagina_actual:Int = 1
    private var total_paginas:Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btAnterior.setOnClickListener {
            if(pagina_actual > 1) {
                cargarPagina(pagina_actual - 1)
            }
        }
        binding.btSiguiente.setOnClickListener {
            if(pagina_actual < total_paginas) {
                cargarPagina(pagina_actual + 1)
            }
        }

        initRecyclerView()
        initApiService()

        cargarPagina(pagina_actual)
    }

    private fun initRecyclerView() {
        adapter = PeliculasAdapter(peliculas)
        binding.rvPeliculas.layoutManager = LinearLayoutManager(this)
        binding.rvPeliculas.adapter = adapter
    }

    private fun initApiService() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(APIService::class.java)
    }

    private fun cargarPagina(pagina: Int) {
        CoroutineScope(Dispatchers.IO).launch  {
            val call: Response<PeliculasResponse> = apiService.getPeliculas(pagina, API_KEY).execute()
            val response:PeliculasResponse? = call.body()
            runOnUiThread() {
                if(call.isSuccessful) {
                    total_paginas = response?.total_pages ?: 1
                    actualizarPagina(pagina)

                    peliculas.clear()
                    peliculas.addAll(response?.results ?: emptyList())
                    adapter.notifyDataSetChanged()
                } else {
                    peliculas.clear()
                    adapter.notifyDataSetChanged()
                    showErrorDialog()
                }
            }
        }
    }

    private fun showErrorDialog() {
        Toast.makeText(this, "Ocurrio un erro!",Toast.LENGTH_SHORT).show()
    }

    private fun actualizarPagina(pagina:Int) {
        pagina_actual = pagina

        binding.tvPagina.text = "Página " + pagina_actual + " de " + total_paginas
        if(pagina_actual == 1) {
            binding.btAnterior.visibility = View.INVISIBLE
        } else {
            binding.btAnterior.visibility = View.VISIBLE
        }
        if(pagina_actual == total_paginas) {
            binding.btSiguiente.visibility = View.INVISIBLE
        } else {
            binding.btSiguiente.visibility = View.VISIBLE
        }
    }

}