package com.mufti.bangkit.learn.ilt3.example.di

import android.content.Context
import com.mufti.bangkit.learn.ilt3.example.data.UserRepository
import com.mufti.bangkit.learn.ilt3.example.data.local.room.UserDatabase
import com.mufti.bangkit.learn.ilt3.example.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val database = UserDatabase.getInstance(context)
        val dao = database.userDao()
        return UserRepository.getInstance(apiService, dao)
    }
}