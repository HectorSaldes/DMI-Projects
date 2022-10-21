package com.saldana.ejerciciosb.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saldana.ejerciciosb.mvvm.provider.FigurasProvider

class FigurasViewModel : ViewModel() {
    var resultado: MutableLiveData<String> = MutableLiveData()


    fun circulo(radio: String) {
        resultado.postValue(FigurasProvider.circulo(radio))
    }


    fun triangulo(base: String, altura: String) {
        resultado.postValue(FigurasProvider.triangulo(base, altura))
    }


    fun cuadrado(lado: String) {
        resultado.postValue(FigurasProvider.cuadrado(lado))

    }

}