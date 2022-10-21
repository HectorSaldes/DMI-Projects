package com.saldana.ejerciciosb.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saldana.ejerciciosb.retrofit.datatype.ResultType
import com.saldana.ejerciciosb.retrofit.provider.ConsultaProvider
import com.saldana.ejerciciosb.retrofit.provider.EliminarProvider
import kotlinx.coroutines.launch

class EliminarViewModel : ViewModel() {
    var data: MutableLiveData<Boolean> = MutableLiveData()
    var isApiProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun delete(id: String) {
        isApiProgress.postValue(true)
        viewModelScope.launch {
            val response = EliminarProvider.delete(id);
            if (response.resultType == ResultType.SUCCESS) {
                isApiProgress.postValue(false)
                data.value = (response.data!!)
            } else {
                isApiProgress.postValue(false)
                data.value = (response.data!!)
            }
        }
    }
}