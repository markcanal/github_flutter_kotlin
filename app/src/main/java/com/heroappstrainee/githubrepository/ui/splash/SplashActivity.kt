package com.heroappstrainee.githubrepository.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.heroappstrainee.githubrepository.MainActivity
import com.heroappstrainee.githubrepository.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class SplashActivity : AppCompatActivity() {
    private val activityScoped = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startSplash()
    }

    private fun startSplash() {
        Timber.i("Splash Screen Activated!")
        activityScoped.launch {
            delay(Constants.LIFE_SPAN)
            finish()
            callMainActivity()
        }
    }

    private fun callMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}