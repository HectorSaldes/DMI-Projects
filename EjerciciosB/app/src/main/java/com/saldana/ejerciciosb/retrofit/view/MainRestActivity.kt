package com.saldana.ejerciciosb.retrofit.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saldana.ejerciciosb.databinding.ActivityMainRestBinding

class MainRestActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainRestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainRestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnConsulta.setOnClickListener { startActivity(Intent(this, RestActivity::class.java)) }
        binding.btnRegistro.setOnClickListener { startActivity(Intent(this, RegistroRestActivity::class.java)) }
    }
}