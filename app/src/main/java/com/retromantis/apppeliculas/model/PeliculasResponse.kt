package com.retromantis.apppeliculas.model

data class PeliculasResponse(
    val dates:Dates,
    val results:List<Result>,
    val page:Int,
    val total_pages:Int,
    val total_results:Int
)

//class PeliculasResponse {
//
//    lateinit var dates   : Dates
//    lateinit var results : List<Result>
//    var page          : Int = 0
//    var total_pages   : Int = 0
//    var total_results : Int = 0
//
//}