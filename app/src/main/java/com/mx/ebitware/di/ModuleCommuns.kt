package com.mx.ebitware.di

import com.mx.ebitware.bd.AppRoomDataBase
import org.koin.dsl.module

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

val moduleCommuns = module {


    //TODO igual va sharedPreferences
    single(createdAtStart = true) { AppRoomDataBase.getInstance(get())}

}

