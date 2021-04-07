package com.retromantis.apppeliculas.app

import com.retromantis.apppeliculas.model.PeliculasResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("upcoming")
    fun getPeliculas(@Query("page") page: Int, @Query("api_key") api_key: String): Call<PeliculasResponse>
}