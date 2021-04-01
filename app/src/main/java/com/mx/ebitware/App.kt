package com.mx.ebitware

import android.app.Application
import com.mx.ebitware.di.moduleCommuns
import com.mx.ebitware.di.moduleNetwork
import com.mx.ebitware.di.modulePresenter
import com.mx.ebitware.di.moduleRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(moduleCommuns, moduleNetwork, modulePresenter, moduleRepository))
        }
    }
}