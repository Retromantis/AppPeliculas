package com.retromantis.apppeliculas.app

import android.app.Application
import com.retromantis.apppeliculas.model.Pelicula

class App : Application() {

    companion object {
        lateinit var pelicula:Pelicula
    }

}