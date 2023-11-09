package com.example.starwarspedia.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.starwarspedia.R
import com.example.starwarspedia.databinding.ActivityMainBinding
import com.example.starwarspedia.presentation.fragments.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, MainFragment())
                .commit()
        }
    }
}