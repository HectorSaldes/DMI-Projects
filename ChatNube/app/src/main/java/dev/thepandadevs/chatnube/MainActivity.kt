package dev.thepandadevs.chatnube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import dev.thepandadevs.chatnube.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var model = mutableListOf<ChatModel>()
    lateinit var adapter: ChatAdapter

    var int: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnviar.setOnClickListener {
            /* enviaMensaje(
                 ChatModel(
                     binding.etMensaje.editText?.text.toString(),
                     if (int % 2 == 0) "pasajero" else "taxi"
                 )
             )*/
            enviarMensajeFirebase(
                ChatModel(
                    binding.etMensaje.editText?.text.toString(),
                    "pasajero"
                )
            )
            binding.etMensaje.editText?.text?.clear()
            int++
        }
        FirebaseDatabase
            .getInstance()
            .getReference("mensajes")
            .child("viaje1")
            .limitToLast(1) // limita a 1 el numero de mensajes que se muestran en el evento, si no me da todo el listado
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    var mensaje = snapshot.child("mensaje").value.toString()
                    var usuario = snapshot.child("usuario").value.toString()
                    if (usuario != "pasajero") {
                        enviaMensaje(ChatModel(mensaje, usuario))
                    }
                }
                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })



        setData()


    }

    private fun enviarMensajeFirebase(data: ChatModel) {
        enviaMensaje(data)
        FirebaseDatabase
            .getInstance()
            .getReference("mensajes")
            .child("viaje1")
            .setValue(model)
            .addOnSuccessListener {
                Toast.makeText(this, "Mensaje enviado", Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener {
                Toast.makeText(this, "Erros ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun enviaMensaje(data: ChatModel) {
        adapter.add(data)
        model.add(data)
        adapter.notifyDataSetChanged()
        binding.lvIMensajes.setSelection(adapter.count - 1)
    }

    fun setData() {
        adapter = ChatAdapter(this)
        binding.lvIMensajes.adapter = adapter
        adapter.notifyDataSetChanged()
        cargarListaMensajes()
    }

    private fun cargarListaMensajes() {
        FirebaseDatabase
            .getInstance()
            .getReference("mensajes")
            .child("viaje1")
            .get()
            .addOnSuccessListener {
                if (it.exists()) {
                    for (item in it.children) {
                        val mensaje = item.child("mensaje").value.toString()
                        val usuario = item.child("usuario").value.toString()
                        enviaMensaje(ChatModel(mensaje, usuario))
                    }

                    /*model = it.getValue() as MutableList<ChatModel>
                    model.forEach {
                        adapter.add(it)
                    }*/
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}