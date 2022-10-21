package com.saldana.ejerciciosb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saldana.ejerciciosb.databinding.ActivityMainBinding
import com.saldana.ejerciciosb.vistas.Persona
import com.saldana.ejerciciosb.vistas.SaludoActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnviar.setOnClickListener {
            var persona = Persona(binding.etNombre.text.toString(),binding.etPaterno.text.toString() )
            val intent = Intent(this@MainActivity, SaludoActivity::class.java)
            intent.putExtra(SaludoActivity.persona, persona)
            startActivity(intent)
        }

    }
}