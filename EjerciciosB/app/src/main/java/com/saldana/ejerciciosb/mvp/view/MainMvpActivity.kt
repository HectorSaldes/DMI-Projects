package com.saldana.ejerciciosb.mvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.saldana.ejerciciosb.databinding.ActivityMainMvpBinding
import com.saldana.ejerciciosb.mvp.presenter.MainPresenter


/*
* MVP (model view presenter)
* interactor (modelo): hace las tareas pesadas
* presentar: (intermediario) conecta vista con interactor
* view: vistas
* */


class MainMvpActivity : AppCompatActivity(), MainInterface {

    lateinit var binding: ActivityMainMvpBinding

    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMvpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter(this)

        binding.btnDivision.setOnClickListener { operation("/") }
        binding.btnMultiplicacion.setOnClickListener { operation("*") }
        binding.btnSumar.setOnClickListener { operation("+") }

    }

    fun operation(operation: String) {
        presenter.makeOperation(
            binding.etNumberX.editText?.text.toString(),
            binding.etNumberY.editText?.text.toString(),
            operation
        )
    }

    override fun showError(error: String) {
        binding.tvResultado.text = "Error"
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun showResult(result: String) {
        binding.tvResultado.text = result
    }
}


