package com.retromantis.apppeliculas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.retromantis.apppeliculas.R
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
//    private val API_KEY  = "f46b58478f489737ad5a4651a4b25079"
    private val API_KEY  = "ec52c6bb69d5e9dddab334efe8f45e96"

    private lateinit var binding:ActivityPeliculasBinding
    private lateinit var apiService: APIService
    private lateinit var adapter: PeliculasAdapter
    private val peliculas = mutableListOf<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initApiService()

        cargarPeliculas(3)
    }

//    private fun getRetrofit(): Retrofit {
//        return Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//    }

    private fun initRecyclerView() {
        println(">>> initRecyclerView")
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

    private fun cargarPeliculas(pagina: Int) {
        CoroutineScope(Dispatchers.IO).launch  {
            val call: Response<PeliculasResponse> = apiService.getPeliculas(pagina, API_KEY).execute()
            val response:PeliculasResponse? = call.body()
            runOnUiThread() {
                if(call.isSuccessful) {
                    peliculas.clear()
                    peliculas.addAll(response?.results ?: emptyList())
                    adapter.notifyDataSetChanged()
                    println(">>> " + peliculas.size + " >>> " + peliculas[0].title ?: ">>> empty")
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
}