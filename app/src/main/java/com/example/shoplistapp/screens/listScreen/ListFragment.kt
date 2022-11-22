package com.example.shoplistapp.screens.listScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplistapp.R
import com.example.shoplistapp.adapter.ShopItemAdapter
import com.example.shoplistapp.databinding.FragmentListBinding
import com.google.android.material.appbar.MaterialToolbar


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ListFragmentViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: MaterialToolbar
    private lateinit var adapter: ShopItemAdapter

    companion object {

        const val SHOP_ITEM_KEY = "SHOP_ITEM"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ListFragmentViewModel::class.java]
        recyclerView = binding.rvShopList
        toolbar = binding.toolbarList

        viewModel.initDB()

        adapter = ShopItemAdapter()
        recyclerView.adapter = adapter

        viewModel.getShopList().observe(viewLifecycleOwner) {

            adapter.setList(it.asReversed())

            setupListeners()

            if (it.isEmpty() || it == null) {

                binding.tvNoList.visibility = View.VISIBLE
                binding.tvAdd.visibility = View.VISIBLE
                binding.fabAddUp.visibility = View.VISIBLE
                binding.fab.visibility = View.INVISIBLE
            } else {

                binding.tvNoList.visibility = View.INVISIBLE
                binding.tvAdd.visibility = View.INVISIBLE
                binding.fabAddUp.visibility = View.INVISIBLE
                binding.fab.visibility = View.VISIBLE
            }
        }

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

               viewModel.deleteShopList()
                recyclerView.post {

                    adapter.notifyDataSetChanged()
                }

            } else if (it.itemId == R.id.item_info) {

                findNavController().navigate(R.id.action_listFragment_to_helpFragment)
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


    private fun moveToAddFragment() {

        findNavController().navigate(R.id.action_listFragment_to_addFragment)
    }


}