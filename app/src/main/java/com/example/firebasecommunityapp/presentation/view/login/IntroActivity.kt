package com.example.firebasecommunityapp.presentation.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.firebasecommunityapp.R
import com.example.firebasecommunityapp.databinding.ActivityIntroBinding
import com.example.firebasecommunityapp.presentation.view.signup.activity.SignUpActivity

class IntroActivity : AppCompatActivity() {
    private lateinit var binding : ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_intro)

        binding.intro = this

    }

    fun signUpPage(){
        startActivity(Intent(this, SignUpActivity::class.java))
    }
}