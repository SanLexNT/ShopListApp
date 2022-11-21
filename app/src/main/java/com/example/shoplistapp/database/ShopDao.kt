package com.example.shoplistapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoplistapp.model.ShopItem

@Dao
interface ShopDao {

    @Query("SELECT * FROM shopItems")
    fun getShopList() : LiveData<List<ShopItem>>

    @Query("SELECT * FROM shopItems WHERE id=:id")
    fun getShopItemById(id: Int) : LiveData<ShopItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addShopItem(shopItem: ShopItem)

    @Update
    fun editShopItem(shopItem: ShopItem)

    @Delete
    fun deleteShopItem(id: Int)

    @Query("DELETE FROM shopItems")
    fun deleteShopList(shopList: List<ShopItem>?)
}