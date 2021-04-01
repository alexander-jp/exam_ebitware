package com.mx.ebitware.bd.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

@Entity(tableName = "Clientes")
data class ClienteEntity(

    @PrimaryKey
    var clientId: Int? = null,

    @ColumnInfo(name = "nombre")
    var nombre: String? = null,

    @ColumnInfo(name = "apellidos")
    var apellidos: String? = null,

    @ColumnInfo(name = "nombre_usuario")
    var nombreUsuario: String? = null,

    @ColumnInfo(name = "correo_electronico")
    var correoElectronico: String? = null,

    @ColumnInfo(name = "contrasenia")
    var contrasenia: String? = null,

    @ColumnInfo(name = "edad")
    var edad: Int? = null,

    @ColumnInfo(name = "sexo")
    var sexo: String? = null,

    @ColumnInfo(name = "peso")
    var peso: Double? = null
) : Serializable