package dev.thepandadevs.saldanaunidaddos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import dev.thepandadevs.saldanaunidaddos.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {


    var player: String = ""
    var tablero: Array<ArrayList<Button>>? = null

    lateinit var binding: ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onListerTablero()

        tablero = arrayOf(
            arrayListOf(binding.btn11, binding.btn12, binding.btn13),
            arrayListOf(binding.btn21, binding.btn22, binding.btn23),
            arrayListOf(binding.btn31, binding.btn32, binding.btn33)
        )

        for (i in 0..2) {
            for (j in 0..2) {
                tablero!![i][j].setOnClickListener { setOnTablero(i, j) }
            }
            player = intent.getStringExtra("player").toString()
            binding.tvJugador.text = "Jugador: $player"
        }

        binding.btnLimpiar.setOnClickListener {
            limpiarTablero()
        }


    }

    private fun limpiarTablero() {
        for (i in 0..2) {
            for (j in 0..2) {
                tablero!![i][j].text = ""
                tablero!![i][j].isEnabled = true
            }
        }
        binding.tbGanador.visibility = View.GONE
        binding.tvJugador.text = "No hay nada seleccionado"
    }

    private fun onListerTablero() {
        FirebaseDatabase.getInstance()
            .getReference("juego")
            .child("partida")
            .limitToLast(1)
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                }

                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    val thisPlayer = snapshot.child("player").value.toString()
                    if (thisPlayer != player) {
                        val row = snapshot.child("row").value.toString().toInt()
                        val column = snapshot.child("column").value.toString().toInt()
                        tablero!![row][column].text = thisPlayer
                        tablero!![row][column].isEnabled = false
                        checkWinner()
                    }
                }

                override fun onChildRemoved(snapshot: DataSnapshot) {
                }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
    }

    private fun checkWinner() {
        var winner = ""
        for (i in 0..2) {
            if (tablero!![i][0].text == tablero!![i][1].text && tablero!![i][0].text == tablero!![i][2].text && tablero!![i][0].text != "") {
                winner = tablero!![i][0].text.toString()
            }
        }
        for (i in 0..2) {
            if (tablero!![0][i].text == tablero!![1][i].text && tablero!![0][i].text == tablero!![2][i].text && tablero!![0][i].text != "") {
                winner = tablero!![0][i].text.toString()
            }
        }
        if (tablero!![0][0].text == tablero!![1][1].text && tablero!![0][0].text == tablero!![2][2].text && tablero!![0][0].text != "") {
            winner = tablero!![0][0].text.toString()
        }
        if (tablero!![0][2].text == tablero!![1][1].text && tablero!![0][2].text == tablero!![2][0].text && tablero!![0][2].text != "") {
            winner = tablero!![0][2].text.toString()
        }
        if (winner != "") {
            Toast.makeText(this, "Ganador: $winner", Toast.LENGTH_SHORT).show()
            setWinner(winner)
        }
    }

    private fun setWinner(winner: String) {
        FirebaseDatabase
            .getInstance()
            .getReference("juego")
            .child("ganador")
            .setValue(winner)
            .addOnSuccessListener {
                binding.tbGanador.visibility = View.VISIBLE
                binding.tbGanador.text = "Ganador: $winner"
            }
            .addOnFailureListener { }
    }

    private fun setOnTablero(i: Int, j: Int) {
        tablero!![i][j].text = player
        tablero!![i][j].isEnabled = false
        checkWinner()
        FirebaseDatabase.getInstance()
            .getReference("juego")
            .child("partida")
            .child("turno")
            .setValue(Turn(player, i, j))
            .addOnSuccessListener {
                Toast.makeText(this, "Turno guardado", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error + ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
