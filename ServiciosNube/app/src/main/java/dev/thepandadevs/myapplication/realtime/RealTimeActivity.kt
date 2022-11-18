package dev.thepandadevs.myapplication.realtime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import dev.thepandadevs.myapplication.databinding.ActivityRealTimeBinding

class RealTimeActivity : AppCompatActivity() {
    lateinit var binding: ActivityRealTimeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRealTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistro.setOnClickListener {
            guardarEnRealTime(
                binding.etNombre.editText?.text.toString(),
                binding.etApellidoPaterno.editText?.text.toString(),
                binding.etApellidoMaterno.editText?.text.toString(),
                binding.etUsername.editText?.text.toString(),
            )
        }

        binding.btnConsulta.setOnClickListener {
            buscarPorUsername(binding.etUsername.editText?.text.toString())
        }

        binding.btnConsultaGeneral.setOnClickListener {
           // consultaGeneral()
            startActivity(Intent(this, ConsultaGeneralRealTimeActivity::class.java))
        }
    }

    private fun consultaGeneral(){
        FirebaseDatabase
            .getInstance()
            .getReference("usuarios")
            .get()
            .addOnSuccessListener {
                for (item in it.children){
                    Log.i("FIREBASE REAL TIME", item.value.toString())
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun buscarPorUsername(username: String) {
            FirebaseDatabase
                .getInstance()
                .getReference("usuarios")
                .child(username)
                .get()
                .addOnSuccessListener {
                    if(it.exists()){
                        // Obtener datos
                        val nombre = it.child("nombre").value.toString()
                        val paterno = it.child("paterno").value.toString()
                        val materno = it.child("materno").value.toString()
                        binding.etNombre.editText?.setText(nombre)
                        binding.etApellidoPaterno.editText?.setText(paterno)
                        binding.etApellidoMaterno.editText?.setText(materno)
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                }
    }

    private fun guardarEnRealTime(nombre: String, paterno: String, materno: String, username: String) {
        val modelo = UsuarioRealTime(nombre, paterno, materno, username)

        FirebaseDatabase
            .getInstance()
            .getReference("usuarios")
            .child(username)
            .setValue(modelo)
            .addOnSuccessListener {
                Toast.makeText(this, "Registro correcto", Toast.LENGTH_SHORT).show()
                binding.etNombre.editText?.text?.clear()
                binding.etApellidoPaterno.editText?.text?.clear()
                binding.etApellidoMaterno.editText?.text?.clear()
                binding.etUsername.editText?.text?.clear()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_SHORT).show()
            }

    }
}