package com.saldana.ejerciciosb.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saldana.ejerciciosb.retrofit.model.User
import com.saldana.ejerciciosb.retrofit.datatype.ResultType
import com.saldana.ejerciciosb.retrofit.provider.RegistroProvider
import kotlinx.coroutines.launch

class RegistroViewModel :ViewModel(){

    var data: MutableLiveData<User> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()
    var isApiProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun create(user: User) {
        isApiProgress.postValue(true)
        viewModelScope.launch {
            val response = RegistroProvider.create(user);
            if (response.resultType == ResultType.SUCCESS) {
                isApiProgress.postValue(false)
                data.value = (response.data!!)
            } else {
                isApiProgress.postValue(false)
                error.value = (response.error!!)
            }
        }
    }
}