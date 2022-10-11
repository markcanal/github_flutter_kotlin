package com.heroappstrainee.githubrepository.ui.dashboard.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heroappstrainee.githubrepository.data_layer.model.entity.UserEntity
import com.heroappstrainee.githubrepository.data_layer.repository.ApplicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ApplicationRepository) :
    ViewModel() {
    private val _usersLiveData = MutableLiveData<List<UserEntity>>()
    val users: LiveData<List<UserEntity>> = _usersLiveData

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            val users = repository.getAllUsers()
            when (users.isSuccessful) {
                true -> {
                    with(users.body().orEmpty()) {
                        var userList = listOf<UserEntity>()
                        forEach { usersResponse ->
                            userList = userList + usersResponse.toUserEntity()
                        }
//                        forEach { (login,_,_,avatar,_,_,html,_,_,_,_,_,_,_,_,_,_,_)->
//                            userList = userList + UserEntity(login,avatar,html)
//
//                        }
                        _usersLiveData.postValue(userList)
                        Timber.i("## $userList")
                    }
                }
                else -> Timber.e("## ${users.message()}")
            }
        }
    }
}