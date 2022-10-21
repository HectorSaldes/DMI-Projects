package com.saldana.ejerciciosb.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.saldana.ejerciciosb.databinding.ActivityFigurasBinding
import com.saldana.ejerciciosb.mvvm.viewmodel.FigurasViewModel

class FigurasActivity : AppCompatActivity() {
    lateinit var binding: ActivityFigurasBinding

    lateinit var viewModel: FigurasViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFigurasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(FigurasViewModel::class.java)

        binding.btnCirculo.setOnClickListener { viewModel.circulo(binding.etRadio.editText?.text.toString()) }
        binding.btnTriangulo.setOnClickListener {
            viewModel.triangulo(
                binding.etBase.editText?.text.toString(),
                binding.etAltura.editText?.text.toString()
            )
        }
        binding.btnCuadrado.setOnClickListener {
            viewModel.cuadrado(binding.etLado.editText?.text.toString())
        }
        initObserver()

    }

    fun initObserver() {

        viewModel.resultado.observe(this) {
            binding.tvResultado.text = it
        }
    }
}