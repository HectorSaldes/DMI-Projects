package com.saldana.ejerciciosb.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saldana.ejerciciosb.mvvm.provider.RegistroProvider

class RegistroViewModel : ViewModel() {

    var result: MutableLiveData<String> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()


    fun checkLogin(user: String, pass: String) {
        if (RegistroProvider.checkLogin(user, pass)) {
            result.postValue("Todo OK")
        } else {
            error.postValue("Error, verifique sus datos")
        }
    }


}