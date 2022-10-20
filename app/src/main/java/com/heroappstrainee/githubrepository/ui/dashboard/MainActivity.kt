package com.heroappstrainee.githubrepository.ui.dashboard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.heroappstrainee.githubrepository.databinding.ActivityMainBinding
import com.heroappstrainee.githubrepository.ui.dashboard.viewModel.MainViewModel
import com.heroappstrainee.githubrepository.utils.showLongToast
import com.heroappstrainee.githubrepository.utils.showShortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val searchText = binding.searchBoxText
        setContentView(binding.root)
        searchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mainViewModel.searchMeNot(s.toString())

            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
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

        mainViewModel.usersFiltered.observe(this) { userList ->
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
