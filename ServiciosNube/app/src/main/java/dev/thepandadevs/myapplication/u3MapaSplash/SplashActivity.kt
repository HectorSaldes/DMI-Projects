package dev.thepandadevs.myapplication.u3MapaSplash

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import dev.thepandadevs.myapplication.databinding.ActivitySplashBinding
import dev.thepandadevs.myapplication.mapas.MapsActivity

class SplashActivity : AppCompatActivity() {
    var REQUEST = 0
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionsApp()
        } else {
            launchApp()
        }

    }

    private fun launchApp() {
        startActivity(Intent(this, MapsActivity::class.java))
    }


    private fun requestPermissionsApp() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
            ),
            REQUEST
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST -> {

                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    showAlert("Debe dar permisos en la ubicación antes de continuar")

                } else {
                    launchApp()
                }
            }
        }
    }


    private fun showAlert(message: String) {
        var dialog = AlertDialog.Builder(this)
        dialog.setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Ok") { dialog, id ->
                finish()
            }
        var alert = dialog.create()
        alert.show()
    }
}