package com.mx.ebitware.di

import com.mx.ebitware.mvp.client.ClientRepository
import com.mx.ebitware.mvp.client.ClientRepositoryImpl
import org.koin.dsl.module

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

val moduleRepository = module {
    single<ClientRepository>(createdAtStart = true) { ClientRepositoryImpl(get(), get()) }
}
