package com.example.shoplistapp.screens.listScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.shoplistapp.database.ShopListDatabase
import com.example.shoplistapp.model.ShopItem
import com.example.shoplistapp.repository.ShopListRepository
import com.example.shoplistapp.repository.ShopListRepositoryImpl

class ListFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application
    private lateinit var repository: ShopListRepository

    fun initDB(){

        val shopDao = ShopListDatabase.getInstance(context).getDao()

        repository = ShopListRepositoryImpl.getInstance(shopDao)
    }

    fun getShopList() : LiveData<List<ShopItem>>{

        return repository.getShopList()
    }

    fun deleteShopList(){

        repository.deleteShopList()
    }

    fun deleteShopItem(shopItem: ShopItem){

        repository.deleteShopItem(shopItem)
    }

    fun editShopItem(shopItem: ShopItem){

        shopItem.isEnabled = !shopItem.isEnabled

        repository.editShopItem(shopItem)
    }
}