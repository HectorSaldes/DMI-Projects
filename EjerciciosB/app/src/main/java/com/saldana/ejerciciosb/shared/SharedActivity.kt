package com.saldana.ejerciciosb.shared

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.saldana.ejerciciosb.EjerciciosApplication.Companion.shared
import com.saldana.ejerciciosb.databinding.ActivitySharedBinding

class SharedActivity : AppCompatActivity() {
    lateinit var binding: ActivitySharedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            shared.saveUser(
                binding.etUsuario.editText?.text.toString(),
                binding.etContrasena.editText?.text.toString()
            )
        }

        binding.btnConsultar.setOnClickListener {
            Toast.makeText(this, shared.getUser(), Toast.LENGTH_SHORT).show()
        }

        binding.btnEliminar.setOnClickListener {
            shared.wipe()
            Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show()
        }
    }
}