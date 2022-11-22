package com.example.shoplistapp.screens.editScreen

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.shoplistapp.database.ShopListDatabase
import com.example.shoplistapp.model.ShopItem
import com.example.shoplistapp.repository.ShopListRepository
import com.example.shoplistapp.repository.ShopListRepositoryImpl

class EditFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application
    private lateinit var repository: ShopListRepository

    fun init(){

        val dao = ShopListDatabase.getInstance(context).getDao()
        repository = ShopListRepositoryImpl.getInstance(dao)
    }

    fun editShopItem(product: String, count: Int, shopItem: ShopItem){

        shopItem.name = product
        shopItem.count = count

        repository.editShopItem(shopItem)
    }

    fun deleteShopItem(shopItem: ShopItem){

        repository.deleteShopItem(shopItem)
    }

    fun showError(text: String){

        Toast.makeText(context, text,
            Toast.LENGTH_SHORT).show()
    }

    fun parseCount(count: String?): Int {

        return try {
            count?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    fun validateName(product: String) : Boolean{

        return product.isNotBlank()
    }

    fun validateCount(count: Int) : Boolean{

        return count > 0
    }

}