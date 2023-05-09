package com.mufti.bangkit.learn.ilt3.example.data.remote.retrofit

import com.mufti.bangkit.learn.ilt3.example.data.remote.response.UserResponse
import retrofit2.http.*

interface ApiService {
    @GET("api/users")
    suspend fun getListUsers(@Query("page") page: Int): UserResponse
}