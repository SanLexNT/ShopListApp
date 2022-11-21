package com.example.shoplistapp.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoplistapp.*
import com.example.shoplistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        MAIN = this
    }
}