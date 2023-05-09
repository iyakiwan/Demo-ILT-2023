package com.mufti.bangkit.learn.ilt3.example.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mufti.bangkit.learn.ilt3.example.model.User
import com.mufti.bangkit.learn.ilt3.example.data.Result
import com.mufti.bangkit.learn.ilt3.example.data.UserRepository

class MainViewModel(repository: UserRepository) : ViewModel() {

    val listUser: LiveData<PagingData<User>> = repository.getListUser().cachedIn(viewModelScope)
}