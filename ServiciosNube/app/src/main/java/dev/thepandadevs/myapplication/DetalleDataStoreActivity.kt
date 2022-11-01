package dev.thepandadevs.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import dev.thepandadevs.myapplication.databinding.ActivityDetalleDataStoreBinding

class DetalleDataStoreActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetalleDataStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        FirebaseFirestore.getInstance()
            .collection("usuarios")
            .document(id!!)
            .get()
            .addOnSuccessListener {
                fillOnView(
                    UsuarioDataStore(
                        id = id,
                        nombre = it["nombre"].toString(),
                        paterno = it["paterno"].toString(),
                        materno = it["materno"].toString(),
                        edad = it["edad"].toString().toInt(),
                        genero = it["genero"].toString(),
                    )
                )
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al consultar ${it.message}", Toast.LENGTH_SHORT).show()
            }

        binding.btnActualizar.setOnClickListener { update(id) }
    }

    fun update(id: String) {
        val getGenero =
            if (binding.rbHombre.isChecked) "Hombre" else if (binding.rbMujer.isChecked) "Mujer" else "Prefiero no decirlo"
        FirebaseFirestore
            .getInstance()
            .collection("usuarios")
            .document(id)
            .set(
                Usuario(
                    nombre = binding.etNombre.editText?.text.toString(),
                    paterno = binding.etApellidoPaterno.editText?.text.toString(),
                    materno = binding.etApellidoMaterno.editText?.text.toString(),
                    edad = binding.etEdad.editText?.text.toString().toInt(),
                    genero = getGenero
                )
            ).addOnSuccessListener {
                Toast.makeText(this, "Actualización exitosa", Toast.LENGTH_SHORT).show()
                finish()
            }.addOnFailureListener {
                Toast.makeText(
                    this,
                    "Ocurrio un error al actualizar ${it.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun fillOnView(usuario: UsuarioDataStore) {
        binding.etNombre.editText?.setText(usuario.nombre)
        binding.etApellidoPaterno.editText?.setText(usuario.paterno)
        binding.etApellidoMaterno.editText?.setText(usuario.materno)
        binding.etEdad.editText?.setText(usuario.edad.toString())
        if (usuario.genero == "Hombre") binding.rbHombre.isChecked = true
        if (usuario.genero == "Mujer") binding.rbMujer.isChecked = true
    }
}