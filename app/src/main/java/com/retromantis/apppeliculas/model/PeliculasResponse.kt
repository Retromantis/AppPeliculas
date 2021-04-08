package com.retromantis.apppeliculas.model

data class PeliculasResponse(
    val dates:Dates,
    val results:List<Pelicula>,
    val page:Int,
    val total_pages:Int,
    val total_results:Int
)