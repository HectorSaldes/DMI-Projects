package com.saldana.ejerciciosb.mvvm.provider


class RegistroProvider {
    companion object {

        fun checkLogin(user: String, pass: String): Boolean {
            return if (user.isNullOrEmpty() || pass.isNullOrEmpty()) false
            else true
        }

    }
}