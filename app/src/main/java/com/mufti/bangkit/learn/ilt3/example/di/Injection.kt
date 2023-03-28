package com.mufti.bangkit.learn.ilt3.example.di

import com.mufti.bangkit.learn.ilt3.example.data.UserRepository
import com.mufti.bangkit.learn.ilt3.example.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}