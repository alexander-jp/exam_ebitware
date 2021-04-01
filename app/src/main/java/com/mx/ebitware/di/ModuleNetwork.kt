package com.mx.ebitware.di

import android.app.Application
import com.google.gson.GsonBuilder
import com.mx.ebitware.api.sources.ClienteSource
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

val moduleNetwork = module {

    val baseUrl = "http://187.188.122.85:8091"

    //Cache
    single { provideOkHttpCache(get())}

    //OkHttpClient
    single { provideOkHttpClient(get())}

    //RetrofitSource
    single{ provideRetrofitSource<ClienteSource>(baseUrl,get())}

}


private fun provideOkHttpCache(application: Application): Cache {
    val cacheSize = 20 * 1024 * 1024 // 10 MB
    return Cache(application.cacheDir, cacheSize.toLong())
}


private fun provideOkHttpClient(cache: Cache): OkHttpClient {
    val interceptorLogger = HttpLoggingInterceptor()
    interceptorLogger.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .cache(cache)
        .addInterceptor(interceptorLogger)
        .build()
}


private inline fun <reified T> provideRetrofitSource(urlBase:String,okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
        .client(okHttpClient)
        .baseUrl(urlBase)
        .build()
    return retrofit.create(T::class.java)
}
