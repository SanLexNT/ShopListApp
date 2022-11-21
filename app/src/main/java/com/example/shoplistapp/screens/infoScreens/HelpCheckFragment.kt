package com.example.shoplistapp.screens.infoScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.shoplistapp.MAIN
import com.example.shoplistapp.R
import com.example.shoplistapp.databinding.FragmentHelpCheckBinding


class HelpCheckFragment : Fragment() {

    private lateinit var binding: FragmentHelpCheckBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHelpCheckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MAIN.onBackPressedDispatcher.addCallback(viewLifecycleOwner,
        object: OnBackPressedCallback(true){

            override fun handleOnBackPressed() {

                findNavController().popBackStack()
            }

        })

        binding.toolbarCheckHelp.setOnMenuItemClickListener {

            if(it.itemId == R.id.item_home){

                findNavController().navigate(R.id.action_helpCheckFragment_to_listFragment)
            }
            true
        }
    }

}