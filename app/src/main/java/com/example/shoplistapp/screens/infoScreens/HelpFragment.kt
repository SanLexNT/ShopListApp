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
import com.example.shoplistapp.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {

    private lateinit var binding: FragmentHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHelpBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ibCheck.setOnClickListener {

            findNavController().navigate(R.id.action_helpFragment_to_helpCheckFragment)
        }

        binding.ibDelete.setOnClickListener {

            findNavController().navigate(R.id.action_helpFragment_to_helpDeleteFragment)
        }

        binding.toolbarHelp.setOnMenuItemClickListener {

            if(it.itemId == R.id.item_home){

                findNavController().popBackStack()
            }
            true
        }

        MAIN.onBackPressedDispatcher.addCallback(viewLifecycleOwner,
        object: OnBackPressedCallback(true){

            override fun handleOnBackPressed() {

                findNavController().popBackStack()
            }

        })
    }

}