package dev.thepandadevs.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import dev.thepandadevs.myapplication.databinding.ActivityConsultaDataStoreBinding

class ConsultaDataStoreActivity : AppCompatActivity(), AdapterUsuario.Eventos {
    lateinit var binding: ActivityConsultaDataStoreBinding

    var adapter: AdapterUsuario? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultaDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        getAll()
    }

    fun getAll() {
        FirebaseFirestore
            .getInstance()
            .collection("usuarios")
            .get()
            .addOnSuccessListener {
                // it.toObjects(UsuarioDataStore::class.java) Tambien obtiene todos los datos pero no el ID
                var response: List<UsuarioDataStore> = it.map { usuario ->
                    UsuarioDataStore(
                        id = usuario.reference.id,
                        nombre = usuario.data["nombre"].toString(),
                        paterno = usuario.data["paterno"].toString(),
                        materno = usuario.data["materno"].toString(),
                        edad = usuario.data["edad"].toString().toInt(),
                        genero = usuario.data["genero"].toString(),
                    )
                }
                setData(response)
            }.addOnFailureListener {
                Toast.makeText(this, "Error al traer los datos", Toast.LENGTH_SHORT).show()
                Log.e("FB ERROR", it.message.toString())
            }
    }

    fun setData(data: List<UsuarioDataStore>) {
        binding.rvItems.layoutManager = LinearLayoutManager(this)
        adapter = AdapterUsuario(this, this)
        binding.rvItems.adapter = adapter
        adapter!!.submitList(data)
    }

    override fun onItemClick(element: UsuarioDataStore, position: Int) {
        startActivity(
            Intent(this, DetalleDataStoreActivity::class.java).putExtra(
                "id",
                element.id
            )
        )
    }

    override fun onItemDelete(element: UsuarioDataStore, position: Int) {
        FirebaseFirestore
            .getInstance()
            .collection("usuarios")
            .document(element.id)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show()
                getAll()
            }
            .addOnFailureListener {
                Log.e("FB ERROR", it.message.toString())
                Toast.makeText(
                    this,
                    "Ocurrió un error al eliminar",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    override fun onStatusChange(element: UsuarioDataStore, position: Int, estatus: String) {

    }
}