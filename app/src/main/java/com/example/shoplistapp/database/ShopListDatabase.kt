package com.example.shoplistapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoplistapp.model.ShopItem

@Database(entities = [ShopItem::class], version = 1, exportSchema = false)
abstract class ShopListDatabase : RoomDatabase() {

    abstract fun getDao() : ShopDao

    companion object{

        private var instance: ShopListDatabase ?= null

        fun getInstance(context: Context) : ShopListDatabase{

            if(instance == null){

                instance = Room.databaseBuilder(context, ShopListDatabase::class.java, "db")
                    .allowMainThreadQueries()
                    .build()
            }

            return instance as ShopListDatabase

        }

    }

}