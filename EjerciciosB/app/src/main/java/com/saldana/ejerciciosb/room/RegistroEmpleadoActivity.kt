package com.saldana.ejerciciosb.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.saldana.ejerciciosb.EjerciciosApplication.Companion.room
import com.saldana.ejerciciosb.databinding.ActivityRegistroEmpleadoBinding
import kotlinx.coroutines.launch

class RegistroEmpleadoActivity : AppCompatActivity() {


    lateinit var binding: ActivityRegistroEmpleadoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroEmpleadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrar.setOnClickListener {
            try {
                lifecycleScope.launch {
                    room.getEmpleadoDao().insert(
                        EmpleadoEntity(
                            id = null,
                            nombre = binding.etNombre.editText?.text.toString(),
                            paterno = binding.etPaterno.editText?.text.toString(),
                            edad = Integer.parseInt(binding.etEdad.editText?.text.toString())
                        )
                    )
                    Toast.makeText(applicationContext, "Registro exitoso", Toast.LENGTH_SHORT).show()
                }

            } catch (ex: Exception) {
                Log.e("ERROR", ex.toString())
                Toast.makeText(this, "Hubo un error", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnConsulta.setOnClickListener {
            try {
                lifecycleScope.launch{
                    Toast.makeText(
                        applicationContext,
                        "Size -> ${room.getEmpleadoDao().getAll().size}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (ex: Exception) {
                Log.e("ERROR", ex.toString())
                Toast.makeText(this, "Hubo un error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}