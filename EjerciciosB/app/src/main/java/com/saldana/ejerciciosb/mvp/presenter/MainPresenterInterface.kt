package com.saldana.ejerciciosb.mvp.presenter

interface MainPresenterInterface {
    fun makeOperation(numX: String, numY: String, operation:String)

    fun showResult(result: String);

    fun showError(error: String)
}