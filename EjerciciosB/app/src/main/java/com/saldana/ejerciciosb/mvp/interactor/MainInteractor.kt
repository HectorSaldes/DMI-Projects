package com.saldana.ejerciciosb.mvp.interactor

import android.util.Log
import com.saldana.ejerciciosb.mvp.presenter.MainPresenter

// Este interactor pertenece a ese presenter y el presenter pertenece al interactor
class MainInteractor(private val presenter: MainPresenter) : MainInteractorInterface {

    override fun makeOperation(numX: String, numY: String, operation: String) {
        try {
            var _numX = numX.toDouble()
            var _numY = numY.toDouble()
            Log.i("DATO", _numX.toString())
            Log.i("DATO", _numY.toString())
            if (_numX != 0.0 || _numY != 0.0 && operation != "/") {
                var result: Double = 0.0
                when (operation) {
                    "/" -> result = _numX / _numY
                    "*" -> result = _numX * _numY
                    "+" -> result = _numX + _numY
                }
                presenter.showResult(result.toString())
            } else {
                presenter.showError("Indeterminado")
            }
        } catch (ex: Exception) {
            Log.e("ERROR", ex.toString())
            presenter.showError("Los datos no son números")
        }
    }
}