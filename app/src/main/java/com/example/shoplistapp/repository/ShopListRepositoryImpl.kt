package com.example.shoplistapp.repository

import androidx.lifecycle.LiveData
import com.example.shoplistapp.database.ShopDao
import com.example.shoplistapp.model.ShopItem

class ShopListRepositoryImpl(private val shopDao: ShopDao) : ShopListRepository {

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopDao.getShopList()
    }

    override fun getShopItemById(id: Int): LiveData<ShopItem> {
        return shopDao.getShopItemById(id)
    }

    override fun addShopItem(shopItem: ShopItem) {
        shopDao.addShopItem(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        shopDao.editShopItem(shopItem)
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopDao.deleteShopItem(shopItem)
    }

    override fun deleteShopList() {
        shopDao.deleteShopList()
    }

    companion object {
        private var instance: ShopListRepositoryImpl? = null

        fun getInstance(shopDao: ShopDao): ShopListRepositoryImpl {
            if (instance == null) {

                instance = ShopListRepositoryImpl(shopDao)
            }
            return instance as ShopListRepositoryImpl
        }
    }
}