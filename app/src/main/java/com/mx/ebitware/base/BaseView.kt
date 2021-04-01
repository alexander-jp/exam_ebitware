package com.mx.ebitware.base

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

interface BaseView<out T : BasePresenter<*>> {

    fun showError(error: Throwable)

    //val presenter: T
}