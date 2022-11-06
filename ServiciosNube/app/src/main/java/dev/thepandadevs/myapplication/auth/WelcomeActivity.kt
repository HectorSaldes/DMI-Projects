package dev.thepandadevs.myapplication.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.thepandadevs.myapplication.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvCorreo.text = UserShared(this).getEmail()
        binding.tvSesion.text = UserShared(this).getSesion()
        binding.btnCerrar.setOnClickListener {
            UserShared(this).wipe()
            startActivity(Intent(this, LoginFirebaseActivity::class.java))
            finish()
        }
    }
}