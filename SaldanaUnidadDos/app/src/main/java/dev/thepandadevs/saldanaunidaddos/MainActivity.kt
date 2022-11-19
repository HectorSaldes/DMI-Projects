package dev.thepandadevs.saldanaunidaddos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import dev.thepandadevs.saldanaunidaddos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var player: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        thereIsAPlayer()
        binding.btnX.setOnClickListener { setPlayerChar("X", "O") }
        binding.btnO.setOnClickListener { setPlayerChar("O", "X") }

    }

    private fun setPlayerChar(player1: String, player2: String) {
        player = player1
        FirebaseDatabase.getInstance()
            .getReference("juego")
            .child("jugador1")
            .setValue(player)
            .addOnSuccessListener {
                FirebaseDatabase.getInstance()
                    .getReference("juego")
                    .child("jugador2")
                    .setValue(player2)
                    .addOnSuccessListener {
                        val intent = Intent(this, GameActivity::class.java)
                        intent.putExtra("player", player)
                        startActivity(intent)
                    }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error + ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }


    private fun thereIsAPlayer() {
        FirebaseDatabase.getInstance()
            .getReference("juego")
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    Log.i("TAG", "onChildChanged: ${snapshot.key}")
                    if (player.isEmpty() && snapshot.key == "jugador2") {
                        Log.i("TAG", "onChildChanged: ${snapshot.key}")
                        val otherPlayer = snapshot.value.toString()
                        val intent = Intent(this@MainActivity, GameActivity::class.java)
                        intent.putExtra("player", otherPlayer)
                        startActivity(intent)
                    }
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                    TODO("Not yet implemented")
                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                    Log.i("FIREBASE", snapshot.toString())
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.i("FIREBASE", error.toString())
                }

            })
    }

}