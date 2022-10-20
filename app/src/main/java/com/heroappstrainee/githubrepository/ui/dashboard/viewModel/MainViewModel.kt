package com.heroappstrainee.githubrepository.ui.dashboard.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heroappstrainee.githubrepository.data_layer.model.entity.UserEntity
import com.heroappstrainee.githubrepository.data_layer.repository.ApplicationRepository
import com.heroappstrainee.githubrepository.ui.dashboard.MainActivityAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ApplicationRepository) :
    ViewModel() {
    lateinit var mainActivityAdapter: MainActivityAdapter
    private val _usersLiveData = MutableLiveData<List<UserEntity>?>()
    val users: LiveData<List<UserEntity>?> = _usersLiveData

    private val _usersFilteredLiveData = MutableLiveData<List<UserEntity>?>()
    val usersFiltered: LiveData<List<UserEntity>?> = _usersFilteredLiveData

    private val _searchString = MutableLiveData<String>()
    val searchString: LiveData<String> = _searchString

    private var userList = listOf<UserEntity>()

    init {
        loadUsers()
        mainActivityAdapter = MainActivityAdapter()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            val users = repository.getAllUsers()
            when (users.isSuccessful) {
                true -> {
                    with(users.body().orEmpty()) {
                        forEach { usersResponse ->
                            userList = userList + usersResponse.toUserEntity()
                        }
//                        forEach { (login,_,_,avatar,_,_,html,_,_,_,_,_,_,_,_,_,_,_)->
//                            userList = userList + UserEntity(login,avatar,html)
//
//                        }

                        _usersLiveData.postValue(userList)
                        _usersFilteredLiveData.postValue(userList)
                        Timber.i("## $userList")
                    }
                }
                else -> Timber.e("## ${users.raw()}")
            }
        }
    }


    fun searchMeNot(string: String) {
        if (string.isNotEmpty()) {
            val s = _usersLiveData.value?.filter { u -> u.login.contains(string) }
            if (s != null) {
               _usersFilteredLiveData.postValue(s)

            }
        } else
           _usersFilteredLiveData.postValue(userList)
    }


}