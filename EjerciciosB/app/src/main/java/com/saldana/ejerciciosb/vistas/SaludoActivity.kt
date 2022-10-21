package com.saldana.ejerciciosb.vistas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saldana.ejerciciosb.databinding.ActivitySaludoBinding

class SaludoActivity : AppCompatActivity() {

    companion object {
        val nombre = "NOMBRE"
        val apellido = "APELLIDO"
        val persona = "PERSONA"
    }

    lateinit var binding: ActivitySaludoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaludoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Obtenemos los datos y los agregamos a la vista
        var persona: Persona = intent.getSerializableExtra(persona) as Persona
        binding.xmlPersona = persona

        // binding.txtNombre.text = persona.nombre
        // binding.txtApellido.text = persona.paterno

    }
}