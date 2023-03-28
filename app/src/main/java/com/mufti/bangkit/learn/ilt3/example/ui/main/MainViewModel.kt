package com.mufti.bangkit.learn.ilt3.example.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mufti.bangkit.learn.ilt3.example.model.User
import com.mufti.bangkit.learn.ilt3.example.data.Result
import com.mufti.bangkit.learn.ilt3.example.data.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {
    private val _listUser = MutableLiveData<Result<List<User>>>()
    val listUser: LiveData<Result<List<User>>> = _listUser

    fun getListUser() {
        _listUser.value = Result.Loading
        viewModelScope.launch {
            _listUser.value = repository.getListUser()
        }
    }
}