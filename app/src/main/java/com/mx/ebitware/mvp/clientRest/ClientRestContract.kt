package com.mx.ebitware.mvp.clientRest

import com.mx.ebitware.base.BasePresenter
import com.mx.ebitware.base.BaseView
import com.mx.ebitware.bd.entity.ClienteEntity

interface ClientRestContract {
    interface View : BaseView<Presenter> {
        fun resultGetClient(response: List<ClienteEntity>)
    }

    interface Presenter : BasePresenter<View> {
        fun getClient()
    }
}