package com.pandadevs.superhector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.pandadevs.superhector.RoomApplication.Companion.room
import com.pandadevs.superhector.databinding.ActivityConsultaBinding
import kotlinx.coroutines.launch

class ConsultaActivity : AppCompatActivity() {

    lateinit var binding: ActivityConsultaBinding
    var adapter: HeroeAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            lifecycleScope.launch {
                val list = room.getSuperHeroes().getAll()
                binding.rvItems.layoutManager = LinearLayoutManager(applicationContext)
                adapter = HeroeAdapter(applicationContext)
                binding.rvItems.adapter = adapter
                adapter!!.submitList(list)
                adapter!!.notifyDataSetChanged()
            }
        } catch (ex: Exception) {
            Log.e("ERROR", ex.toString())
            Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show()
        }
    }
}