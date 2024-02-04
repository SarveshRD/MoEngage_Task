package com.wss.eat_space_iz.ui.activity.splashScreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.wss.eat_space_iz.databinding.ActivitySplashScreenBinding
import com.wss.eat_space_iz.ui.activity.loginSignUp.LoginSignUpActivity
import com.wss.eat_space_iz.ui.activity.main.MainActivity
import com.wss.eat_space_iz.ui.activity.onBoarding.OnBoardingActivity
import com.wss.eat_space_iz.utils.sessionManagers.OnBoardingSessionManager
import com.wss.eat_space_iz.utils.sessionManagers.UserSessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    @Inject
    lateinit var onBoardingSessionManager: OnBoardingSessionManager

    @Inject
    lateinit var prefs: UserSessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        view.hideSoftKeyboard()


        Handler(Looper.getMainLooper()).postDelayed({
            if (with(onBoardingSessionManager) { chooseAFavouriteFood && receiveTheGreatFood && hotDeliveryToHome }) {
                if ((prefs.isUserLogin))
                    moveUserHomeIntent()
                else {
                    loadLoginSignUpActivity()
                }
            } else {
                moveOnBoardingIntent()
            }
        }, 3000)

    }

    private fun loadLoginSignUpActivity() {
        val intent = Intent(this, LoginSignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun moveUserHomeIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun moveOnBoardingIntent() {
        val intent = Intent(this, OnBoardingActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun View.hideSoftKeyboard() {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    fun View.showSoftKeyboard() {
        if (requestFocus())
            (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
                this,
                InputMethodManager.SHOW_IMPLICIT
            )
    }

}