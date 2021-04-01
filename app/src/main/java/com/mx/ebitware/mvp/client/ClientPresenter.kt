package com.mx.ebitware.mvp.client

import com.mx.ebitware.api.request.ClienteRequestBean
import com.mx.ebitware.base.RxPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

class ClientPresenter(private val repo: ClientRepository) : RxPresenter<ClientContract.View>(),
    ClientContract.Presenter {
    override var view: ClientContract.View? = null

    override fun subscribe(view: ClientContract.View) {
        this.view = view
    }


    override fun addClient(req: ClienteRequestBean) {
        launch {
            repo.addCliente(req)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.resultAddClient(it)
                }, {
                    view?.showError(it)
                })
        }
    }

    override fun addClientLocal(req: ClienteRequestBean) {
        launch {
            repo.addClienteDB(req)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.resultAddClient(it)
                }, {
                    view?.showError(it)
                })
        }
    }

    override fun editClient(req: ClienteRequestBean) {
        launch {
            repo.editCliente(req)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.resultEditClient(it)
                }, {
                    view?.showError(it)
                })
        }
    }

    override fun editClientLocal(req: ClienteRequestBean) {
        launch {
            repo.editClienteDB(req)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.resultEditClient(it)
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