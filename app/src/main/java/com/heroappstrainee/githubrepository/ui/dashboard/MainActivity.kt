package com.heroappstrainee.githubrepository.ui.dashboard

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.heroappstrainee.githubrepository.databinding.ActivityMainBinding
import com.heroappstrainee.githubrepository.ui.dashboard.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
        setUpObserver()
    }

    private fun setUpUI() {
        binding.userCollectionView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = MainActivityAdapter()
        }
    }

    private fun setUpObserver() {
        mainViewModel.users.observe(this) { userList ->
            userList?.let {
                binding.userCollectionView.apply {
                    with(adapter as MainActivityAdapter) {
                        usersList = it
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }
}