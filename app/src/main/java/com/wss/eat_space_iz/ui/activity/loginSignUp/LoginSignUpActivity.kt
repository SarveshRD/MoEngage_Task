package com.wss.eat_space_iz.ui.activity.loginSignUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wss.eat_space_iz.databinding.ActivityLoginSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginSignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}