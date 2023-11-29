package com.template.easypayments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.template.easypayments.databinding.ActivityMainBinding
import com.template.easypayments.presentation.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}