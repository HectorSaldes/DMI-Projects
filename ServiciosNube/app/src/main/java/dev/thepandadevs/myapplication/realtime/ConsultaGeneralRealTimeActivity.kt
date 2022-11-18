package dev.thepandadevs.myapplication.realtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import dev.thepandadevs.myapplication.databinding.ActivityConsultaGeneralRealTimeBinding

class ConsultaGeneralRealTimeActivity : AppCompatActivity(), AdapterUsuario.Eventos {
    lateinit var binding: ActivityConsultaGeneralRealTimeBinding
    var adapter: AdapterUsuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultaGeneralRealTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        consultaGeneral()
    }


    private fun consultaGeneral() {
        FirebaseDatabase
            .getInstance()
            .getReference("usuarios")
            .get()
            .addOnSuccessListener {
                val list = mutableListOf<UsuarioRealTime>()
                for (item in it.children) {
                    list.add(
                        UsuarioRealTime(
                            item.child("nombre").value.toString(),
                            item.child("paterno").value.toString(),
                            item.child("materno").value.toString(),
                            item.child("username").value.toString(),
                        )
                    )
//                    Log.i("FIREBASE REAL TIME", item.value.toString())
                }
                setData(list)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    fun setData(data: List<UsuarioRealTime>) {
        binding.rvItems.layoutManager = LinearLayoutManager(this)
        adapter = AdapterUsuario(this, this)
        binding.rvItems.adapter = adapter
        adapter!!.submitList(data)
    }

    override fun onItemClick(element: UsuarioRealTime, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemDelete(element: UsuarioRealTime, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onStatusChange(element: UsuarioRealTime, position: Int, estatus: String) {
        TODO("Not yet implemented")
    }
}