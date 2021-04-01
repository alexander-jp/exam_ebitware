package com.mx.ebitware.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mx.ebitware.R
import com.mx.ebitware.bd.entity.ClienteEntity
import com.mx.ebitware.databinding.ItemListClientBinding
import com.mx.ebitware.interfaces.OnItemClickListener

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

class AdapterListClient : RecyclerView.Adapter<AdapterListClient.VH>() {

    var list: ArrayList<ClienteEntity> = arrayListOf()
    var onclick : OnItemClickListener?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_list_client, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val c = list[position]
        holder.name.text = "Nombre del cliente: ${c.nombre}"
        holder.lastName.text = "Apellidos: ${c.apellidos}"
        holder.email.text = "Correo Electronico: ${c.correoElectronico}"
        holder.nameUser.text = "Nombre de usuario :${c.nombreUsuario ?:" NULL"}"
        holder.itemView.setOnClickListener {
            onclick?.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = list.size

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        private val _binding by lazy { ItemListClientBinding.bind(v) }

        val name = _binding.tvNameClient
        val lastName = _binding.tvLastNameClient
        val nameUser = _binding.tvNameUserClient
        val email = _binding.tvEmailClient
    }
}