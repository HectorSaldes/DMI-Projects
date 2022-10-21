package com.saldana.ejerciciosb.listas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.saldana.ejerciciosb.databinding.ActivityListaBinding

class ListaActivity : AppCompatActivity(), AdapterAlumno.Eventos {

    lateinit var binding: ActivityListaBinding

    var data: MutableList<Alumno> = mutableListOf()

    var adapter: AdapterAlumno? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val API_URL: String = "https://avatars.dicebear.com/api/miniavs"

        data.add(
            Alumno(
                nombre = "Hector",
                "$API_URL/${(1..500).random()}.png",
                "Activo",
                "21 años"
            )
        )
        data.add(
            Alumno(
                nombre = "Armando",
                "$API_URL/${(1..500).random()}.png",
                "Desactivado",
                "12 años"
            )
        )
        data.add(
            Alumno(
                nombre = "Alexis",
                "$API_URL/${(1..500).random()}.png",
                "Inactivo",
                "23 años"
            )
        )
        data.add(
            Alumno(
                "Daniel",
                "$API_URL/${(1..500).random()}.png",
                "Activo",
                "62 años"
            )
        )
        data.add(
            Alumno(
                "Alitzel",
                "$API_URL/${(1..500).random()}.png",
                "Inactivo",
                "21 años"

            )
        )

        setData()
    }

    fun setData() {

        binding.rvLista.layoutManager = LinearLayoutManager(this)
        adapter = AdapterAlumno(this, this)
        binding.rvLista.adapter = adapter
        adapter!!.submitList(data) // !! seguro de que no es null
        adapter!!.notifyDataSetChanged()

        /*
        UNA LISTA DE SOLO STRINGS

        var data: List<String> = listOf("Hector", "Alexis", "Raúl", "José", "Luis", "Jair")
        var adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1 , data)
           binding.lvList.adapter = adapter
           adapter.notifyDataSetChanged()
           binding.lvList.setOnItemClickListener { adapterView, view, i, l ->
               Toast.makeText(this, data[i], Toast.LENGTH_SHORT).show()
           }*/
    }

    override fun onItemClick(element: Alumno, position: Int) {
        //TODO("Not yet implemented")
    }

    override fun onStatusChange(element: Alumno, position: Int, estatus: String) {
        // Actualizamos nuestro elemento en la vista
        data[position].estatus = estatus
        adapter!!.notifyItemChanged(position)
        Toast.makeText(this, element.nombre, Toast.LENGTH_SHORT).show()
    }
}