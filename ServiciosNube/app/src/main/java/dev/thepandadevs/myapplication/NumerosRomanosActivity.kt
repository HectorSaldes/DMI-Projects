package dev.thepandadevs.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.thepandadevs.myapplication.databinding.ActivityNumerosRomanosBinding

class NumerosRomanosActivity : AppCompatActivity() {
    lateinit var binding: ActivityNumerosRomanosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumerosRomanosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnConvertir.setOnClickListener { validations() }
    }

    // function to convert a number in a roman number
    private fun convertirNumeroRomano(numero: Int): String {
        var numeroRomano = ""
        var numero = numero
        val numerosRomanos = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
        val numerosArabigos = arrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
        for (i in numerosArabigos.indices) {
            while (numero >= numerosArabigos[i]) {
                numeroRomano += numerosRomanos[i]
                numero -= numerosArabigos[i]
            }
        }
        return numeroRomano
    }

    fun validations() {
        var result: String = ""
        var number = binding.btnNumero.editText?.text.toString().toInt()
        if (number in 1..100) {
            result = convert(number)
            binding.tvResultado.text = result
        } else Toast.makeText(this, "Número fuera del rango", Toast.LENGTH_SHORT).show()
    }

    fun convert(number: Int): String {
        var result = ""
        if (number == 100) return "C";
        if (number == 50) return "L"
        if (number == 10) return "X"
        if (number in 10..19) result += "X"
        if (number in 20..29) result += "XX"
        if (number in 30..39) result += "XXX"
        if (number in 40..49) result += "XXXX"
        if (number in 50..59) result += "L"
        if (number in 60..69) result += "LX"
        if (number in 70..79) result += "LXX"
        if (number in 80..89) result += "LXXX"
        if (number in 90..99) result += "XC"
        result += hasWhat(number, '9', "IX")
        result += hasWhat(number, '8', "VIII")
        result += hasWhat(number, '7', "VII")
        result += hasWhat(number, '6', "VI")
        result += hasWhat(number, '5', "V")
        result += hasWhat(number, '4', "IV")
        result += hasWhat(number, '3', "III")
        result += hasWhat(number, '2', "II")
        result += hasWhat(number, '1', "I")
        return result
    }

    fun hasWhat(number: Int, search: Char, respondeWith: String): String {
        val hasThat = number.toString()[number.toString().length - 1]
        if (hasThat == search) return respondeWith
        return "";
    }
}