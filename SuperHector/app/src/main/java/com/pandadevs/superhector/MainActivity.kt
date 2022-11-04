package com.pandadevs.superhector

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.pandadevs.superhector.RoomApplication.Companion.room
import com.pandadevs.superhector.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistrar.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
        binding.btnConsultar.setOnClickListener {
            try {
                lifecycleScope.launch {
                    if (room.getSuperHeroes().getAll().isEmpty()) {
                        Toast.makeText(
                            applicationContext,
                            "No hay registros todavía",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        startActivity(Intent(applicationContext, ConsultaActivity::class.java))
                    }
                }
            } catch (ex: Exception) {
                Log.e("ERROR", ex.toString())
                Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}