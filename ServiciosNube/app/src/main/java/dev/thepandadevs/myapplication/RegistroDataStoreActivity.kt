package dev.thepandadevs.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import dev.thepandadevs.myapplication.databinding.ActivityRegistroDataStoreBinding

class RegistroDataStoreActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistroDataStoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistro.setOnClickListener {
            if (validate()) {
                val getGenero =
                    if (binding.rbHombre.isChecked) "Hombre" else if (binding.rbMujer.isChecked) "Mujer" else "Prefiero no decirlo"
                // Registrar en la colleción usuarios
                FirebaseFirestore
                    .getInstance()
                    .collection("usuarios")
                    .add(
                        Usuario(
                            nombre = binding.etNombre.editText?.text.toString(),
                            paterno = binding.etApellidoPaterno.editText?.text.toString(),
                            materno = binding.etApellidoMaterno.editText?.text.toString(),
                            edad = binding.etEdad.editText?.text.toString().toInt(),
                            genero = getGenero
                        )
                    ).addOnSuccessListener {
                        Toast.makeText(this, "Registro correcto: ${it.id}", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    }.addOnFailureListener {
                        Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
                    }
            } else Toast.makeText(this, "Algunos campos estan vacios", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validate(): Boolean {
        return !binding.etNombre.editText?.text.isNullOrEmpty()
                && !binding.etApellidoPaterno.editText?.text.isNullOrEmpty()
                && !binding.etApellidoMaterno.editText?.text.isNullOrEmpty()
                && !binding.etEdad.editText?.text.isNullOrEmpty()
    }
}