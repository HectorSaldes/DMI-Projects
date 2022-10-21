package com.saldana.ejerciciosb.mvp.presenter

import com.saldana.ejerciciosb.mvp.interactor.MainInteractor
import com.saldana.ejerciciosb.mvp.view.MainMvpActivity


// Indicamos que este presenter es con esta actividad o se relacionan
class MainPresenter(private val viewActivity: MainMvpActivity) : MainPresenterInterface {

    val interactor = MainInteractor(this)

    override fun makeOperation(numX: String, numY: String, operation: String) {
        interactor.makeOperation(numX, numY, operation)
    }

    override fun showResult(result: String) {
        viewActivity.showResult(result)
    }

    override fun showError(error: String) {
        viewActivity.showError(error)
    }
}