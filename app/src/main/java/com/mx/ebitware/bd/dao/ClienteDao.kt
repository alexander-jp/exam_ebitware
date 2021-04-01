package com.mx.ebitware.bd.dao

import androidx.room.*
import com.mx.ebitware.bd.entity.ClienteEntity

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

@Dao
interface ClienteDao {

    @Query("SELECT * FROM clientes")
    fun getAllClientes() : List<ClienteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addClient(req : ClienteEntity)

    @Update
    fun editClient(req : ClienteEntity)




}