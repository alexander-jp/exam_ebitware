package com.mx.ebitware.mvp.clientLocal

import com.mx.ebitware.base.RxPresenter
import com.mx.ebitware.mvp.client.ClientRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

class ClientLocalPresenter(private val repo : ClientRepository) : RxPresenter<ClientLocalContract.View>(), ClientLocalContract.Presenter {

    override var view: ClientLocalContract.View? = null

    override fun subscribe(view: ClientLocalContract.View) {
        this.view = view
        super.subscribe(view)
    }

    override fun getClients() {
        launch {
            repo.getClienteDB()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.resultGetClients(it)
                }, {
                    view?.showError(it)
                })
        }
    }

    override fun unSubscribe() {
        this.view = null
        super.unSubscribe()
    }
}