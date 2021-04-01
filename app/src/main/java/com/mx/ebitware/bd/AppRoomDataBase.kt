package com.mx.ebitware.bd

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mx.ebitware.bd.dao.ClienteDao
import com.mx.ebitware.bd.entity.ClienteEntity

/**
 * Created by Alexander Juárez with Date 31/03/2021
 * @author Alexander Juárez
 */

@Database(entities = [ClienteEntity::class], version = 1, exportSchema = false)
public abstract class AppRoomDataBase : RoomDatabase() {

    abstract fun clienteDao(): ClienteDao

    companion object {

        @Volatile
        private var INSTANCE: AppRoomDataBase? = null

        fun getInstance(application: Application): AppRoomDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    application,
                    AppRoomDataBase::class.java,
                    "ebitware.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }

        }
    }
}

