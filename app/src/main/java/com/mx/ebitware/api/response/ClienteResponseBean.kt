package com.mx.ebitware.api.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

class ClienteResponseBean {

    @SerializedName("Cliente_ID")
    var Cliente_ID: Int? = null

    @SerializedName("Nombre")
    var Nombre: String? = null

    @SerializedName("Apellidos")
    var Apellidos: String? = null

    @SerializedName("Nombre_Usuario")
    var Nombre_Usuario: String? = null

    @SerializedName("Correo_Electronico")
    var Correo_Electronico: String? = null

    @SerializedName("Contraseña")
    var Contraseña: String? = null

}