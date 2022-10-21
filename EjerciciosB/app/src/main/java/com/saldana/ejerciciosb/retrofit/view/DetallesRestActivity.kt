package com.saldana.ejerciciosb.retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.saldana.ejerciciosb.databinding.ActivityDetallesRestBinding
import com.saldana.ejerciciosb.retrofit.model.User
import com.saldana.ejerciciosb.retrofit.viewmodel.ActualizarViewModel
import com.saldana.ejerciciosb.retrofit.viewmodel.ConsultaViewModel
import com.saldana.ejerciciosb.retrofit.viewmodel.EliminarViewModel
import com.saldana.ejerciciosb.utils.LoadingScreen

class DetallesRestActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetallesRestBinding

    lateinit var viewModel: ConsultaViewModel

    lateinit var viewModelActualizar: ActualizarViewModel

    lateinit var viewModelEliminarViewModel: EliminarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesRestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ConsultaViewModel::class.java)
        viewModelActualizar = ViewModelProvider(this).get(ActualizarViewModel::class.java)
        viewModelEliminarViewModel = ViewModelProvider(this).get(EliminarViewModel::class.java)


        val id = intent.getStringExtra("ID")

        viewModel.getOne(id.toString())

        initObservers()

        binding.btnActualizar.setOnClickListener {
            viewModelActualizar.update(
                id.toString(),
                User(
                    id = binding.etId.editText?.text.toString().toInt(),
                    name = binding.etNombre.editText?.text.toString(),
                    email = binding.etCorreo.editText?.text.toString(),
                    gender = binding.etGenero.editText?.text.toString(),
                    status = "active"
                )
            )
        }

        binding.btnEliminar.setOnClickListener {
            viewModelEliminarViewModel.delete(id.toString());
        }


        /*lifecycleScope.launch {
            var call = ApiUtils.apiService.getOne(
                "users/$id",
                "Bearer ff6e10581285e15947a88eacb48223c9a58b588fe5a1814f85b19e445cbdf7f1"
            )
            runOnUiThread {
                if (call.isSuccessful) {
                    Log.i("API", id.toString())
                    binding.tvNombre.text = call.body()!!.name
                    binding.tvCorreo.text = call.body()!!.email
                    binding.tvGenero.text = call.body()!!.gender
                    binding.tvEstado.text = call.body()!!.status
                } else {
                    Log.e("ERROR", "HUBO UN ERROR EN LA PETICION")
                }
            }
        }*/


    }

    private fun initObservers() {
        viewModel.dataOne.observe(this) {
            binding.etId.editText!!.setText(it.id.toString())
            binding.etNombre.editText!!.setText(it.name.toString())
            binding.etCorreo.editText!!.setText(it.email.toString())
            binding.etGenero.editText!!.setText(it.gender.toString())
        }

        viewModel.isApiProgress.observe(this) {
            if (it) {
                LoadingScreen.show(this@DetallesRestActivity, "Cargando", false)
            } else {
                LoadingScreen.hide()
            }
        }

        viewModel.error.observe(this) {
            Toast.makeText(this@DetallesRestActivity, it, Toast.LENGTH_SHORT).show()
        }

        viewModelActualizar.isApiProgress.observe(this) {
            if (it) {
                LoadingScreen.show(this@DetallesRestActivity, "Cargando", false)
            } else {
                LoadingScreen.hide()
            }
        }

        viewModelActualizar.error.observe(this) {
            Toast.makeText(this@DetallesRestActivity, it, Toast.LENGTH_SHORT).show()
        }

        viewModelEliminarViewModel.data.observe(this) {
            if (it) {
                Toast.makeText(this@DetallesRestActivity, "Usuario eliminado", Toast.LENGTH_SHORT)
                    .show()
                finish()
            } else {
                Toast.makeText(
                    this@DetallesRestActivity,
                    "Ocurrió un error al eliminar",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        viewModelEliminarViewModel.isApiProgress.observe(this) {
            if (it) {
                LoadingScreen.show(this@DetallesRestActivity, "Cargando", false)
            } else {
                LoadingScreen.hide()
            }
        }
    }
    /* // Instancia de retrofit TODO AHORA ESTA EN ApiService.kt
     private fun getRetrofit(): Retrofit {
         return Retrofit
             .Builder()
             .baseUrl("https://gorest.co.in/public/v2/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
     }*/
}