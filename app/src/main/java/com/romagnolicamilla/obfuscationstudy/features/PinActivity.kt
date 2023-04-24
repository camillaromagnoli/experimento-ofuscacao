package com.romagnolicamilla.obfuscationstudy.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.romagnolicamilla.obfuscationstudy.databinding.ActivityPinBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPinBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}