package dev.thepandadevs.chatnube.permisos

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import dev.thepandadevs.chatnube.databinding.ActivityPermisosBinding

class PermisosActivity : AppCompatActivity() {

    lateinit var binding: ActivityPermisosBinding

    var REQUEST = 0

    var permisosEspanol = arrayOf(
        "Camara, ",
        "Audio, ",
        "Ubicacion, ",
        "Llamadas, ",
        "Almancenamiento, "
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermisosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPermisos.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                solicitarPermisos()
            } else {
                lanzarAplicacion()
            }
        }
    }

    private fun lanzarAplicacion() {
        Toast.makeText(this, "Todo ok", Toast.LENGTH_SHORT).show()
    }

    private fun solicitarPermisos() {
        // Muestra el dialog para permitir los permisos en la aplicación de camara
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            REQUEST // le mandamos nuestra clave de permiso
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var permisos = ""
        for (per in permissions) Log.i("PERMISOS_MAN", per)
        for (grant in grantResults) Log.i("PERMISOS_MAN_INT", grant.toString())

        permissions.forEachIndexed { index, per ->
            when (requestCode) {
                REQUEST -> {   // Si el codigo de permiso es igual al que le mandamos secrectamente
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {
                        permisos += permisosEspanol[index]
                    }
                }
            }
        }
        if (permisos.isNotEmpty()) {
            mostrarAlertaPermisoNoConcedido(permisos)
        } else {
            lanzarAplicacion()
        }
    }

    private fun mostrarAlertaPermisoNoConcedido(permisos: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage("El usuario no concedió los permisos de: $permisos")
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, id ->
                dialog.cancel()
            }

        val alerta = dialog.create()
        alerta.show()
    }

}