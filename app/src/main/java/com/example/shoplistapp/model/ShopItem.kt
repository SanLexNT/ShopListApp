package com.example.shoplistapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopItems")
data class ShopItem(

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,

    val name: String,
    val count: Int,
    var isEnabled: Boolean = true
)
