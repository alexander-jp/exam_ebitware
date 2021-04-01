package com.mx.ebitware.di

import com.mx.ebitware.mvp.client.ClientContract
import com.mx.ebitware.mvp.client.ClientPresenter
import com.mx.ebitware.mvp.clientLocal.ClientLocalContract
import com.mx.ebitware.mvp.clientLocal.ClientLocalPresenter
import com.mx.ebitware.mvp.clientRest.ClienRestPresenter
import com.mx.ebitware.mvp.clientRest.ClientRestContract
import org.koin.dsl.module

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

val modulePresenter = module {
    single<ClientRestContract.Presenter> (createdAtStart = true){ ClienRestPresenter(get()) }
    single<ClientContract.Presenter>(createdAtStart = true) { ClientPresenter(get()) }
    single<ClientLocalContract.Presenter>(createdAtStart = true){ ClientLocalPresenter(get()) }
}