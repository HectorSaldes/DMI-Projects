package com.saldana.ejerciciosb.mvvm.provider

import java.lang.Exception

class FigurasProvider {
    companion object {

        fun circulo(radio: String): String {
            return if (radio.isNullOrEmpty()) {
                ("Favor ingresar un valor")
            } else {
                try {
                    var _radio: Float = radio.toFloat();
                    var resultado: String
                    resultado = "AREA: "+(3.1416 * (_radio * _radio)).toFloat().toString()
                    resultado += "\nPERIMETRO: ${((2 * 3.1416 * _radio).toFloat())}"
                    return resultado
                } catch (ex: Exception) {
                    return ("Imposible convertir a numero")
                }
            }
        }


        fun triangulo(base: String, altura: String): String {
            if (base.isNullOrEmpty() || altura.isNullOrEmpty()) {
                return ("Favor ingresar un valor")
            } else {
                try {
                    var _base: Float = base.toFloat();
                    var _altura: Float = altura.toFloat();
                    var resultado: String
                    resultado = "AREA: "+(((_base * _altura) / 2).toString())
                    resultado += "\nPERIMETO: ${(_base * 3)}"
                    return resultado
                } catch (ex: Exception) {
                    return ("Imposible convertir a numero")
                }
            }
        }


        fun cuadrado(lado: String): String {
            if (lado.isNullOrEmpty()) {
                return ("Favor ingresar un valor")
            } else {
                try {
                    var _lado: Float = lado.toFloat();
                    var resultado: String
                    resultado = "AREA: "+((_lado * _lado).toString())
                    resultado += "\nPERIMETRO: ${((_lado * 4))}"
                    return resultado
                } catch (ex: Exception) {
                    return ("Imposible convertir a numero")
                }
            }

        }
    }
}