package com.mx.ebitware.mvp.clientRest

import com.mx.ebitware.base.RxPresenter
import com.mx.ebitware.mvp.client.ClientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

class ClienRestPresenter(private val repo: ClientRepository) :
    RxPresenter<ClientRestContract.View>(),
    ClientRestContract.Presenter {

    override var view: ClientRestContract.View? = null

    override fun subscribe(view: ClientRestContract.View) {
        this.view = view
    }


    override fun getClient() {
        launch {
            repo.getCliente()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.resultGetClient(it)
                }, {
                    view?.showError(it)
                })
        }
    }

    override fun unSubscribe() {
        super.unSubscribe()
        this.view = null
    }
}