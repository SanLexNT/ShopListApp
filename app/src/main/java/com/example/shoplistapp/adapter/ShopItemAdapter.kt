package com.example.shoplistapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.shoplistapp.R
import com.example.shoplistapp.model.ShopItem
import com.example.shoplistapp.screens.listScreen.ListFragmentViewModel
import kotlinx.android.synthetic.main.item_shop_item_layout.view.*
import kotlinx.coroutines.launch

class ShopItemAdapter() : Adapter<ShopListViewHolder>() {

    var shopItems = emptyList<ShopItem>()
    var onClickListener : ((shopItem: ShopItem) -> Unit) ?= null
    var onChangeListener : ((shopItem : ShopItem) -> Unit) ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop_item_layout, parent, false)
        return ShopListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        val shopItem = shopItems[position]

        holder.itemView.tv_shop_item.text = shopItem.name
        holder.itemView.tv_count_item.text = "Количество: ${shopItem.count}"
        holder.itemView.chb_is_active.isChecked = shopItem.isEnabled

        holder.itemView.chb_is_active.setOnCheckedChangeListener { _, _ ->

            onChangeListener?.invoke(shopItem)
        }
    }

    override fun getItemCount(): Int {
        return shopItems.size
    }

    fun setList(shopList: List<ShopItem>){

        shopItems = shopList
        notifyItemRangeChanged(0, itemCount)
    }

    override fun onViewAttachedToWindow(holder: ShopListViewHolder) {
        super.onViewAttachedToWindow(holder)

        holder.itemView.setOnClickListener {

            onClickListener?.invoke(shopItems[holder.adapterPosition])
        }
    }

}