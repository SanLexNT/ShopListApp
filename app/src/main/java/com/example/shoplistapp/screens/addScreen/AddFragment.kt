package com.example.shoplistapp.screens.addScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoplistapp.MAIN
import com.example.shoplistapp.R
import com.example.shoplistapp.databinding.FragmentAddBinding
import com.example.shoplistapp.model.ShopItem
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.fragment_add.*

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

        setupCheckInput()

        toolbar.setOnMenuItemClickListener {

            if (it.itemId == R.id.item_done) {

                val product = viewModel.parseProduct(binding.nameEt.text.toString())
                val count = viewModel.parseCount(binding.countEt.text.toString())

                if(viewModel.validateName(product) && viewModel.validateCount(count)){

                    val shopItem = ShopItem(name = product, count = count)
                    viewModel.addShopItem(shopItem)
                    moveBack()
                } else{

                    viewModel.showError(resources.getString(R.string.error_incorrect_data))
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

    private fun setupCheckInput() {
        binding.nameEt.setOnFocusChangeListener { _, isFocused ->

            if (!isFocused) {

                if (!viewModel.validateName(nameEt.text.toString())) {

                    binding.tilNameAdd.helperText = resources.getString(R.string.error_name_field)
                } else {

                    binding.tilNameAdd.helperText = null
                }

            }
        }

        binding.countEt.setOnFocusChangeListener { _, isFocused ->

            if (!isFocused) {

                if (binding.countEt.text.toString().isEmpty()) {

                    binding.tilCountAdd.helperText = resources.getString(R.string.error_count_field)
                } else {

                    val count = viewModel.parseCount(countEt.text.toString())

                    if (!viewModel.validateCount(count)) {

                        binding.tilCountAdd.helperText = getString(R.string.error_count_size)
                    } else {

                        binding.tilCountAdd.helperText = null
                    }
                }
            }
        }
    }

    private fun moveBack() {

        findNavController().popBackStack()
    }

}