package com.saldana.ejerciciosb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saldana.ejerciciosb.databinding.ActivityMenuBinding
import com.saldana.ejerciciosb.listas.ListaActivity
import com.saldana.ejerciciosb.mvp.view.MainMvpActivity
import com.saldana.ejerciciosb.mvvm.view.FigurasActivity
import com.saldana.ejerciciosb.mvvm.view.RegistroMvvmActivity
import com.saldana.ejerciciosb.retrofit.view.MainRestActivity
import com.saldana.ejerciciosb.room.RegistroEmpleadoActivity
import com.saldana.ejerciciosb.shared.SharedActivity
import com.saldana.ejerciciosb.splash.SplashActivity

class MenuActivity : AppCompatActivity() {

    lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnMenu.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        binding.btnSplash.setOnClickListener { startActivity(Intent(this, SplashActivity::class.java)) }
        binding.btnList.setOnClickListener { startActivity(Intent(this, ListaActivity::class.java)) }
        binding.btnRoom.setOnClickListener { startActivity(Intent(this, RegistroEmpleadoActivity::class.java)) }
        binding.btnCalcu.setOnClickListener { startActivity(Intent(this, MainMvpActivity::class.java)) }
        binding.btnFormulario.setOnClickListener { startActivity(Intent(this, RegistroMvvmActivity::class.java)) }
        binding.btnFiguras.setOnClickListener { startActivity(Intent(this, FigurasActivity::class.java)) }
        binding.btnShared.setOnClickListener { startActivity(Intent(this, SharedActivity::class.java)) }
        binding.btnRestApi.setOnClickListener { startActivity(Intent(this, MainRestActivity::class.java)) }
    }
}