package dev.thepandadevs.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.thepandadevs.myapplication.databinding.ActivityDataStoreBinding

class DataStoreActivity : AppCompatActivity() {
    lateinit var binding: ActivityDataStoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegistro.setOnClickListener { startActivity(Intent(this, RegistroDataStoreActivity::class.java)) }
        binding.btnConsulta.setOnClickListener { startActivity(Intent(this, ConsultaDataStoreActivity::class.java)) }
    }
}