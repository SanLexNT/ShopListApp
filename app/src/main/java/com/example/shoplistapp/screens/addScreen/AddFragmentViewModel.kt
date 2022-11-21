package com.example.shoplistapp.screens.addScreen

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.shoplistapp.R
import com.example.shoplistapp.database.ShopListDatabase
import com.example.shoplistapp.model.ShopItem
import com.example.shoplistapp.repository.ShopListRepository
import com.example.shoplistapp.repository.ShopListRepositoryImpl

class AddFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application
    private lateinit var repository: ShopListRepository

    fun init() {

        val dao = ShopListDatabase.getInstance(context).getDao()

        repository = ShopListRepositoryImpl.getInstance(dao)
    }

    fun addShopItem(shopItem: ShopItem) {

        repository.addShopItem(shopItem)
    }

    fun showError(){

        Toast.makeText(context, context.getString(R.string.error_incorrect_data),
                Toast.LENGTH_SHORT).show()
    }

    fun parseProduct(product: String?): String {

        return product?.trim() ?: ""
    }

    fun parseCount(count: String?): Int {

        return try {
            count?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    fun validateInput(product: String, count: Int) : Boolean{

        return product.isNotBlank() && count > 0
    }

}