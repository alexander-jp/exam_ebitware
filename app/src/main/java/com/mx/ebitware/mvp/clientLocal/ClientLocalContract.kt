package com.mx.ebitware.mvp.clientLocal

import com.mx.ebitware.base.BasePresenter
import com.mx.ebitware.base.BaseView
import com.mx.ebitware.bd.entity.ClienteEntity

interface ClientLocalContract {
    interface View : BaseView<Presenter> {
        fun resultGetClients(response : List<ClienteEntity>)
    }

    interface Presenter : BasePresenter<View> {
        fun getClients()
    }
}