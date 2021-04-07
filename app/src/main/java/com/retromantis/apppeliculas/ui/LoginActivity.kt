package com.retromantis.apppeliculas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.retromantis.apppeliculas.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btIngresar.setOnClickListener {
            verificarCampos()
        }
    }

    private fun verificarCampos() {
        val user     = binding.etUsuario.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if(user.isEmpty() || password.isEmpty()) {
            showToast("Complete los campos")
            return
        }
        if(!user.equals("Admin")) {
            showToast("Usuario Incorrecto")
            return
        }
        if(!password.equals("Password*123")) {
            showToast("Password Incorrecto")
            return
        }

        startActivity(Intent(this, PeliculasActivity::class.java))
    }

    private fun showToast(text:String) {
        val toast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }
}