package com.example.shoplistapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.shoplistapp.R
import com.example.shoplistapp.model.ShopItem
import com.example.shoplistapp.screens.listScreen.ListFragmentViewModel
import kotlinx.android.synthetic.main.item_shop_item_layout.view.*

class ShopItemAdapter(private val viewModel : ListFragmentViewModel) : Adapter<ShopListViewHolder>() {

    var shopItems = emptyList<ShopItem>()

    var onClickListener : ((shopItem: ShopItem) -> Unit) ?= null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shop_item_layout, parent, false)
        return ShopListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        val shopItem = shopItems[position]

        holder.itemView.tv_shop_item.text = shopItem.name
        holder.itemView.tv_count_item.text = shopItem.count.toString()
        holder.itemView.chb_is_active.isChecked = shopItem.isEnabled

        holder.itemView.chb_is_active.setOnCheckedChangeListener { compoundButton, b ->

            viewModel.changeStateShopItem(shopItem)
            notifyItemChanged(position)
        }

        holder.itemView.setOnClickListener {

            onClickListener?.invoke(shopItem)
        }

    }

    override fun getItemCount(): Int {
        return shopItems.size
    }

    fun setList(shopList: List<ShopItem>){

        shopItems = shopList
        notifyItemRangeChanged(0, itemCount)
    }


}