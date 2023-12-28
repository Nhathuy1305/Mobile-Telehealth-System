package com.main.mobiletelehealthsystem.ui.introduction

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.main.mobiletelehealthsystem.ui.auth.signInScreen.SignInScreen
import com.main.mobiletelehealthsystem.utils.Logger
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.main.mobiletelehealthsystem.R
import com.main.mobiletelehealthsystem.ui.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class Splashscreen : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance(); //initialize Firebase Auth
        val currentUser: FirebaseUser? = auth.currentUser //Get the current user
        Logger.debugLog("Current User: $currentUser")

        if (currentUser == null) sendUserToLoginActivity() //If the user has not logged in, send them to On-Boarding Activity
        else {
            //If user was logged in last time
            CoroutineScope(Dispatchers.Main).launch {
                //If the user has logged in, send them to Home Activity
                delay(2000L)
                val loginIntent =
                   Intent(
                        this@Splashscreen,
                        HomeActivity::class.java
                    ) //If the user email is verified
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(loginIntent)
                finish()
            }
        }
    }

    private fun sendUserToLoginActivity() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000L)
            val loginIntent = Intent(
                this@Splashscreen,
                SignInScreen::class.java
            )
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(loginIntent)
            finish()
        }
    }

}