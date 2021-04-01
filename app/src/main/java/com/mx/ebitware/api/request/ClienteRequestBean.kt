package com.mx.ebitware.api.request

import com.google.gson.annotations.SerializedName

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

data class ClienteRequestBean(

    @SerializedName("Cliente_ID")
    var Cliente_ID: Int? = null,

    @SerializedName("Nombre")
    var Nombre: String? = null,

    @SerializedName("Apellidos")
    var Apellidos: String? = null,

    @SerializedName("Nombre_Usuario")
    var Nombre_Usuario: String? = null,

    @SerializedName("Correo_Electronico")
    var Correo_Electronico: String? = null,

    @SerializedName("Contraseña")
    var Contraseña: String? = null,

    @SerializedName("Edad")
    var Edad: Int? = null,

    @SerializedName("Estatura")
    var Estatura: Double? = null,

    @SerializedName("Peso")
    var Peso: Double? = null,

    @SerializedName("GEB")
    var GEB: Double? = null
)