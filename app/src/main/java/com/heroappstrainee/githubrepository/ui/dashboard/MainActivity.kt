package com.heroappstrainee.githubrepository.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.heroappstrainee.githubrepository.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}