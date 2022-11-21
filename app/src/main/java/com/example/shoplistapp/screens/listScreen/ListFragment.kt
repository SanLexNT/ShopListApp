package com.example.shoplistapp.screens.listScreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplistapp.R
import com.example.shoplistapp.adapter.ShopItemAdapter
import com.example.shoplistapp.databinding.FragmentListBinding
import com.example.shoplistapp.model.ShopItem
import com.google.android.material.appbar.MaterialToolbar


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ListFragmentViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: MaterialToolbar
    private lateinit var adapter: ShopItemAdapter

    companion object{

        const val SHOP_ITEM_KEY = "SHOP_ITEM"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ListFragmentViewModel::class.java]
        recyclerView = binding.rvShopList
        toolbar = binding.toolbarList

        viewModel.initDB()

        adapter = ShopItemAdapter()
        recyclerView.adapter = adapter

        viewModel.getShopList().observe(viewLifecycleOwner){

            adapter.setList(it.asReversed())

            if(it.isEmpty() || it == null){

                binding.tvNoList.visibility = View.VISIBLE
                binding.tvAdd.visibility = View.VISIBLE
                binding.fabAddUp.visibility = View.VISIBLE
                binding.fab.visibility = View.INVISIBLE
            } else{

                binding.tvNoList.visibility = View.INVISIBLE
                binding.tvAdd.visibility = View.INVISIBLE
                binding.fabAddUp.visibility = View.INVISIBLE
                binding.fab.visibility = View.VISIBLE
            }
            setupItemTouchHelper(it)
        }

        setupListeners()
    }

    private fun setupListeners() {

        adapter.onChangeListener = {

            viewModel.editShopItem(it)

            recyclerView.post {

                adapter.notifyItemChanged(it.id)
            }
        }

        adapter.onClickListener = {

            val bundle = Bundle()
            bundle.putSerializable(SHOP_ITEM_KEY, it)

            findNavController().navigate(R.id.action_listFragment_to_editFragment, bundle)
        }

        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.item_delete) {

                //TODO: Диалоговое окно "Точно удалить список?"

                viewModel.deleteShopList()
            } else if (it.itemId == R.id.item_info) {
                //TODO: Переход к окну справки
            }
            true
        }

        binding.fabAddUp.setOnClickListener {

            moveToAddFragment()
        }
        binding.fab.setOnClickListener {

            moveToAddFragment()
        }
    }

    private fun setupItemTouchHelper(it: List<ShopItem>) {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback
            (0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val shopItem = it[viewHolder.adapterPosition]
                //TODO: Диалоговое окно "Точно удалить продукт?"

                viewModel.deleteShopItem(shopItem)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        })
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun moveToAddFragment(){

        findNavController().navigate(R.id.action_listFragment_to_addFragment)
    }
}