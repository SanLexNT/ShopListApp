package com.example.shoplistapp.screens.addScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoplistapp.MAIN
import com.example.shoplistapp.R
import com.example.shoplistapp.databinding.FragmentAddBinding
import com.example.shoplistapp.model.ShopItem
import com.google.android.material.appbar.MaterialToolbar

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var toolbar: MaterialToolbar
    private lateinit var viewModel: AddFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[AddFragmentViewModel::class.java]
        viewModel.init()

        toolbar = binding.toolbarAdd


        toolbar.setOnMenuItemClickListener {

            if (it.itemId == R.id.item_done) {

                val product = viewModel.parseProduct(binding.nameEt.text.toString())
                val count = viewModel.parseCount(binding.countEt.text.toString())

                if (viewModel.validateInput(product, count)) {

                    val shopItem = ShopItem(name = product, count = count)
                    viewModel.addShopItem(shopItem)
                    moveBack()
                } else{

                    viewModel.showError()
                }
            }
            true
        }

        MAIN.onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                    findNavController().popBackStack()
                }
            }
        )
    }

    private fun moveBack(){

        findNavController().popBackStack()
    }

}