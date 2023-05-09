package com.mufti.bangkit.learn.ilt3.example.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.mufti.bangkit.learn.ilt3.example.data.local.room.UserDao
import com.mufti.bangkit.learn.ilt3.example.data.remote.retrofit.ApiService
import com.mufti.bangkit.learn.ilt3.example.model.User
import com.mufti.bangkit.learn.ilt3.example.data.remote.mapper.UserMapper
import com.mufti.bangkit.learn.ilt3.example.data.remote.paging.UserPagingSource

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) {

    fun getListUser(): LiveData<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 6
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userDao: UserDao
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userDao)
            }.also { instance = it }
    }
}