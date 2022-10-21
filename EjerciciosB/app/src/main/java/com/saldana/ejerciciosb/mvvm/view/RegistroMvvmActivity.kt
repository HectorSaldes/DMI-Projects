package com.saldana.ejerciciosb.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.saldana.ejerciciosb.databinding.ActivityRegistroEmpleadoBinding
import com.saldana.ejerciciosb.databinding.ActivityRegistroMvvmBinding
import com.saldana.ejerciciosb.mvvm.viewmodel.RegistroViewModel

class RegistroMvvmActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistroMvvmBinding

    lateinit var viewModel: RegistroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroMvvmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Comunicación a mi RegistroViewModel
        viewModel = ViewModelProvider(this).get(RegistroViewModel::class.java)

        binding.btnRegistrar.setOnClickListener {
            viewModel.checkLogin(
                binding.etUsuario.editText?.text.toString(),
                binding.etPassword.editText?.text.toString()
            )
        }
        initObserver()
    }


    fun initObserver() {
        // Observa result y haz todos los nuevos cambios en este bloque
        viewModel.result.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        }

        // Observa erro y haz todos los nuevos cambios en este bloque
        viewModel.error.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        }
    }

}