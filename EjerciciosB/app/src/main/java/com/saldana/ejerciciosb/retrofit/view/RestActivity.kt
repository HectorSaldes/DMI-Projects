package com.saldana.ejerciciosb.retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.saldana.ejerciciosb.databinding.ActivityRestBinding
import com.saldana.ejerciciosb.retrofit.view.adapters.AdapterUser
import com.saldana.ejerciciosb.retrofit.viewmodel.ConsultaViewModel
import com.saldana.ejerciciosb.utils.LoadingScreen

class RestActivity : AppCompatActivity() {
    lateinit var binding: ActivityRestBinding

    var adapter: AdapterUser? = null

    lateinit var viewModel: ConsultaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ConsultaViewModel::class.java)

        initObservers() // Iniciar observadores

        binding.btnRest.setOnClickListener {
            viewModel.getAll()

            binding.lpiBar.visibility = View.VISIBLE

//            LoadingScreen.show(this, "Esperando", false)


            /* TODO Lanzado con ViewModel
            lifecycleScope.launch {
                 var call = ApiUtils.apiService.getAll("users/")
                 runOnUiThread {
                     binding.lpiBar.visibility = View.INVISIBLE
                     LoadingScreen.hide()
                     if (call.isSuccessful) {
                         binding.rvLista.layoutManager = LinearLayoutManager(applicationContext)
                         adapter = AdapterUser(applicationContext)
                         binding.rvLista.adapter = adapter
                         adapter!!.submitList(call.body())
                         adapter!!.notifyDataSetChanged()
                     } else {
                         Log.e("ERROR", "HUBO UN ERROR EN LA PETICION")
                     }
                 }
             }*/
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

    fun initObservers() {
        viewModel.isApiProgress.observe(this) {
            if (it) {
                binding.lpiBar.visibility = View.VISIBLE
                LoadingScreen.show(this@RestActivity, "Cargando", false)
            } else {
                binding.lpiBar.visibility = View.INVISIBLE
                LoadingScreen.hide()
            }
        }

        viewModel.error.observe(this) {
            Toast.makeText(this@RestActivity, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.data.observe(this) {
            binding.rvLista.layoutManager = LinearLayoutManager(applicationContext)
            adapter = AdapterUser(applicationContext)
            binding.rvLista.adapter = adapter
            adapter!!.submitList(it)
            adapter!!.notifyDataSetChanged()
        }
    }
}