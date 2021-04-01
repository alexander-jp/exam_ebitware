package com.mx.ebitware.api.sources

import com.mx.ebitware.api.request.ClienteRequestBean
import com.mx.ebitware.api.response.ClienteResponseBean
import io.reactivex.Single
import retrofit2.http.*

interface ClienteSource {

    @GET("NutriNET/Cliente")
    @Headers("Content-type: application/json")
    fun getClientes(): Single<List<ClienteResponseBean>>

    @POST("NutriNET/Cliente")
    @Headers("Content-type: application/json")
    fun addCliente(@Body req: ClienteRequestBean): Single<Boolean>

    @PUT("NutriNET/Cliente/{Cliente_ID}")
    @Headers("Content-type: application/json")
    fun editCliente(@Path("Cliente_ID") id: Int, @Body req: ClienteRequestBean): Single<Boolean>
}