package com.mx.ebitware.mvp.client

import com.mx.ebitware.api.request.ClienteRequestBean
import com.mx.ebitware.base.BasePresenter
import com.mx.ebitware.base.BaseView

interface ClientContract {
    interface View : BaseView<Presenter> {
        fun resultAddClient(response : Boolean)
        fun resultEditClient(response: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun addClient(req : ClienteRequestBean)
        fun addClientLocal(req : ClienteRequestBean)
        fun editClient(req : ClienteRequestBean)
        fun editClientLocal(req : ClienteRequestBean)
    }
}