package com.retromantis.apppeliculas.model

data class Result(
    val adult:Boolean,
    val backdrop_path : String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Float,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
)

//class Result {
//
//    var adult: Boolean = false
//    lateinit var backdrop_path : String
//    lateinit var genre_ids: List<Int>
//    var id: Int = 0
//    lateinit var original_language: String
//    lateinit var original_title: String
//    lateinit var overview: String
//    var popularity: Float = 0.0F
//    lateinit var poster_path: String
//    lateinit var release_date: String
//    lateinit var title: String
//    var video: Boolean = false
//    var vote_average: Float = 0.0F
//    var vote_count: Int = 0
//
//    override fun toString() = title
//
//}