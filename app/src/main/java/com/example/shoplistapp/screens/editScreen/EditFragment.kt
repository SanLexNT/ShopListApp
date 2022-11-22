package com.example.shoplistapp.screens.editScreen

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.shoplistapp.MAIN
import com.example.shoplistapp.R
import com.example.shoplistapp.databinding.FragmentEditBinding
import com.example.shoplistapp.model.ShopItem
import com.example.shoplistapp.screens.listScreen.ListFragment
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.fragment_edit.*

class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private lateinit var toolbar: MaterialToolbar
    private lateinit var viewModel: EditFragmentViewModel
    private lateinit var shopItem: ShopItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        shopItem = arguments?.getSerializable(ListFragment.SHOP_ITEM_KEY) as ShopItem
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = binding.toolbarEdit

        binding.nameEtEdit.setText(shopItem.name)
        binding.countEtEdit.setText(shopItem.count.toString())

        viewModel = ViewModelProvider(this)[EditFragmentViewModel::class.java]
        viewModel.init()

        setupCheckInput()

        toolbar.setOnMenuItemClickListener {

            if (it.itemId == R.id.item_done) {

                val product = binding.nameEtEdit.text.toString()
                val count = binding.countEtEdit.text.toString().toInt()

                if(viewModel.validateName(product) && viewModel.validateCount(count)){

                    viewModel.editShopItem(product, count, shopItem)
                    findNavController().popBackStack()
                } else{
                    viewModel.showError(resources.getString(R.string.error_incorrect_data))
                }
            } else if(it.itemId == R.id.item_delete){

               val alertDialogBuilder = AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.confirm_act_dialog))
                    .setMessage(getString(R.string.dialog_delete_item_message))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.dialog_yes)){ _, _ ->

                        viewModel.deleteShopItem(shopItem)
                        findNavController().popBackStack()
                    }
                    .setNegativeButton(getString(R.string.dialog_no)){ dialogInterface: DialogInterface, _ ->

                        dialogInterface.cancel()
                    }

                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()

                alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(resources.getColor(android.R.color.holo_red_dark, null))

            }
            true

        }

        MAIN.onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                    findNavController().popBackStack()
                }
            })
    }

    private fun setupCheckInput() {
        binding.nameEtEdit.setOnFocusChangeListener { _, isFocused ->

            if (!isFocused) {

                if (!viewModel.validateName(nameEt_edit.text.toString())) {

                    binding.tilName.helperText = resources.getString(R.string.error_name_field)
                } else {

                    binding.tilName.helperText = null
                }
            }
        }

        binding.countEtEdit.setOnFocusChangeListener { _, isFocused ->

            if (!isFocused) {

                if (binding.countEtEdit.text.toString().isEmpty()) {

                    binding.tilCount.helperText = resources.getString(R.string.error_count_field)
                } else {

                    val count = viewModel.parseCount(countEt_edit.text.toString())

                    if (!viewModel.validateCount(count)) {

                        binding.tilCount.helperText = getString(R.string.error_count_size)
                    } else {

                        binding.tilName.helperText = null
                    }
                }
            }
        }
    }
}