package com.pandadevs.superhector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.pandadevs.superhector.RoomApplication.Companion.room
import com.pandadevs.superhector.databinding.ActivityRegistroBinding
import com.pandadevs.superhector.room.PersonajeEntity
import kotlinx.coroutines.launch

class RegistroActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrar.setOnClickListener {
            if (validarEntradas()) {
                try {
                    lifecycleScope.launch {
                        room.getSuperHeroes().insert(
                            PersonajeEntity(
                                id = null,
                                nombre = binding.etNombre.editText?.text.toString(),
                                habilidad = binding.etHabilidad.editText?.text.toString(),
                                anio = binding.etAnio.editText?.text.toString()
                            )
                        )
                        Toast.makeText(applicationContext, "Registro exitoso", Toast.LENGTH_SHORT)
                            .show()
                        binding.etNombre.editText?.text?.clear()
                        binding.etHabilidad.editText?.text?.clear()
                        binding.etAnio.editText?.text?.clear()
                        finish()
                    }
                } catch (ex: Exception) {
                    Log.e("ERROR", ex.toString())
                    Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "Existen campos vacios, favor de llenarlos todos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun validarEntradas(): Boolean {
        return !binding.etNombre.editText?.text.toString().isNullOrEmpty() &&
                !binding.etHabilidad.editText?.text.toString().isNullOrEmpty() &&
                !binding.etAnio.editText?.text.toString().isNullOrEmpty()
    }
}