package com.example.shoplistapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "shopItems")
data class ShopItem(

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,

    var name: String,
    var count: Int,
    var isEnabled: Boolean = true
) : Serializable
