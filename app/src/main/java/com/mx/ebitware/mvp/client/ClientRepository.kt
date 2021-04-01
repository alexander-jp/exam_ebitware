package com.mx.ebitware.mvp.client

import android.annotation.SuppressLint
import android.util.Log
import com.mx.ebitware.api.request.ClienteRequestBean
import com.mx.ebitware.api.response.ClienteResponseBean
import com.mx.ebitware.api.sources.ClienteSource
import com.mx.ebitware.bd.AppRoomDataBase
import com.mx.ebitware.bd.entity.ClienteEntity
import io.reactivex.Single

interface ClientRepository {

    fun addCliente(req: ClienteRequestBean): Single<Boolean>
    fun editCliente(req: ClienteRequestBean): Single<Boolean>
    fun getCliente(): Single<List<ClienteEntity>>

    fun addClienteDB(req: ClienteRequestBean): Single<Boolean>
    fun editClienteDB(req: ClienteRequestBean): Single<Boolean>
    fun getClienteDB(): Single<List<ClienteEntity>>

}

class ClientRepositoryImpl(
    private val source: ClienteSource,
    private val dataBase: AppRoomDataBase
) : ClientRepository {

    @SuppressLint("CheckResult")
    override fun addCliente(req: ClienteRequestBean): Single<Boolean> {
        return source.addCliente(req)
            .doOnSuccess {
                Log.e("TAG", "addCliente se ha registrado: $it")
            }
            .doOnError { it.printStackTrace() }
    }

    override fun editCliente(req: ClienteRequestBean): Single<Boolean> {
        return source.editCliente(req.Cliente_ID!!, req)
            .doOnSuccess {
                Log.e("TAG", "editCliente: $it")
            }
            .doOnError { it.printStackTrace() }
    }

    override fun getCliente(): Single<List<ClienteEntity>> {
        val list: ArrayList<ClienteEntity> = arrayListOf()
        return source.getClientes()
            .map { v ->
                v.forEach { c ->
                    val data = ClienteEntity(
                           nombre = c.Nombre,
                           nombreUsuario = c.Nombre_Usuario,
                           correoElectronico = c.Correo_Electronico,
                           contrasenia = c.Contraseña,
                           clientId = c.Cliente_ID,
                           apellidos = c.Apellidos
                    )
                    list.add(data)
                }
                return@map list
            }
    }

    //TODO BD LOCAL CON ROOM

    override fun addClienteDB(req: ClienteRequestBean): Single<Boolean> {
        return Single.create {
            val client = ClienteEntity(
                nombre = req.Nombre,
                apellidos = req.Apellidos,
                edad = req.Edad,
                peso = req.Peso,
                contrasenia = req.Contraseña,
                correoElectronico = req.Correo_Electronico,
                nombreUsuario = req.Nombre_Usuario,
                sexo = "Hombre",
            )
            try {
                dataBase.clienteDao().addClient(client)
                it.onSuccess(true)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    override fun editClienteDB(req: ClienteRequestBean): Single<Boolean> {
        return Single.create {
            val client = ClienteEntity(
                clientId = req.Cliente_ID,
                nombre = req.Nombre,
                apellidos = req.Apellidos,
                edad = req.Edad,
                peso = req.Peso,
                contrasenia = req.Contraseña,
                correoElectronico = req.Correo_Electronico,
                nombreUsuario = req.Nombre_Usuario,
                sexo = "Hombre",
            )
            dataBase.clienteDao().editClient(client)
            it.onSuccess(true)
        }
    }

    override fun getClienteDB(): Single<List<ClienteEntity>> {
        return Single.create {
            try {
                val c = dataBase.clienteDao().getAllClientes()
                it.onSuccess(c)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

}