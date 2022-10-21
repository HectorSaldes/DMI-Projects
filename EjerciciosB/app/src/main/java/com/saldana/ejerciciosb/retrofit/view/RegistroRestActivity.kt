package com.saldana.ejerciciosb.retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.saldana.ejerciciosb.databinding.ActivityRegistroRestBinding
import com.saldana.ejerciciosb.retrofit.model.User
import com.saldana.ejerciciosb.retrofit.viewmodel.RegistroViewModel
import com.saldana.ejerciciosb.utils.LoadingScreen

class RegistroRestActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistroRestBinding

    lateinit var viewModel: RegistroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroRestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this@RegistroRestActivity).get(RegistroViewModel::class.java)
        initObservers()

        binding.btnRegistrar.setOnClickListener {
            viewModel.create(
                User(
                    id = null,
                    name = binding.etNombre.editText?.text.toString(),
                    email = binding.etCorreo.editText?.text.toString(),
                    gender = binding.etGenero.editText?.text.toString(),
                    status = "active"
                )
            )

            /* // TODO Se cambió por el ViewModel
             lifecycleScope.launch {
                 var call = ApiUtils.apiService.create(
                     "users", User(
                         id = null,
                         name = binding.etNombre.editText?.text.toString(),
                         email = binding.etCorreo.editText?.text.toString(),
                         gender = binding.etGenero.editText?.text.toString(),
                         status = "active"
                     ),
                     "Bearer ff6e10581285e15947a88eacb48223c9a58b588fe5a1814f85b19e445cbdf7f1"
                 )
                 runOnUiThread {
                     if (call.isSuccessful) {
                         Log.i("API", call.body()!!.id.toString())
                         Toast.makeText(
                             applicationContext,
                             "Datos registrados correctamente",
                             Toast.LENGTH_SHORT
                         ).show()
                         finish()

                     } else {
                         var gson = GsonBuilder().create() //extraer datos
                         var type = object : TypeToken<List<ErrorData>>() {}.type //El tipo que es
                         var errores: List<ErrorData> =
                             gson.fromJson(
                                 call.errorBody()!!.charStream(),
                                 type
                             ) // Guardamos los errors

                         call.errorBody()
                         Log.e("ERROR", errores[0].toString()) // Imprimimos los errores
                     }
                 }
             }*/
        }
    }

    fun initObservers() {
        viewModel.data.observe(this) {
            Toast.makeText(
                this@RegistroRestActivity,
                "Usuario ${it} registrado",
                Toast.LENGTH_SHORT
            ).show()
        }

        viewModel.error.observe(this) {
            Toast.makeText(this@RegistroRestActivity, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.isApiProgress.observe(this) {
            if (it) {
                LoadingScreen.show(this@RegistroRestActivity, "Cargando", false)
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