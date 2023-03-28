package com.mufti.bangkit.learn.ilt3.example.data.retrofit

import com.mufti.bangkit.learn.ilt3.example.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("api/users")
    fun getListUsers(@Query("page") page: String): Call<UserResponse>
}