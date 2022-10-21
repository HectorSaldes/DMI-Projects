package com.saldana.ejerciciosb.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saldana.ejerciciosb.retrofit.model.User
import com.saldana.ejerciciosb.retrofit.datatype.ResultType
import com.saldana.ejerciciosb.retrofit.provider.ConsultaProvider
import kotlinx.coroutines.launch

class ConsultaViewModel : ViewModel() {

    var data: MutableLiveData<List<User>> = MutableLiveData()


    var dataOne: MutableLiveData<User> = MutableLiveData()


    var error: MutableLiveData<String> = MutableLiveData()
    var isApiProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun getAll() {
        isApiProgress.postValue(true)
        // Corrotina
        viewModelScope.launch {
            val response = ConsultaProvider.getAll();
            if (response.resultType == ResultType.SUCCESS) {
                isApiProgress.postValue(false)
                data.value = (response.data!!)
                //  data.postValue(response.data!!) TODO es lo mismo
            } else {
                isApiProgress.postValue(false)
                error.value = (response.error!!)
            }
        }

    }


    fun getOne(id: String) {
        isApiProgress.postValue(true)
        viewModelScope.launch {
            val response = ConsultaProvider.getOne(id);
            if (response.resultType == ResultType.SUCCESS) {
                isApiProgress.postValue(false)
                dataOne.value = (response.data!!)
            } else {
                isApiProgress.postValue(false)
                error.value = (response.error!!)
            }
        }
    }
}