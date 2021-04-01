package com.mx.ebitware.base

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

interface BasePresenter<V> {

    fun subscribe(view: V)

    fun unSubscribe()

    var view : V?

}