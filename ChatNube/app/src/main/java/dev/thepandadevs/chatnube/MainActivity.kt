package dev.thepandadevs.chatnube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            enviaMensaje(
                ChatModel(
                    binding.etMensaje.editText?.text.toString(),
                    if (int % 2 == 0) "pasajero" else "taxi"
                )
            )
            binding.etMensaje.editText?.text?.clear()
            int++
        }

        setData()

    }

    private fun enviaMensaje(data: ChatModel) {
        adapter.add(data)
        model.add(data)
        adapter.notifyDataSetChanged()
    }

    fun setData() {
        adapter = ChatAdapter(this)
        binding.lvIMensajes.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}