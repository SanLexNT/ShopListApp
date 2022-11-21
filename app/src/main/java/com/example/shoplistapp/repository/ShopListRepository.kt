package com.example.shoplistapp.repository

import androidx.lifecycle.LiveData
import com.example.shoplistapp.model.ShopItem

interface ShopListRepository {

    fun getShopList() : LiveData<List<ShopItem>>
    fun getShopItemById(id: Int) : LiveData<ShopItem>
    fun addShopItem(shopItem: ShopItem)
    fun editShopItem(shopItem: ShopItem)
    fun deleteShopItem(id: Int)
    fun deleteShopList()

}