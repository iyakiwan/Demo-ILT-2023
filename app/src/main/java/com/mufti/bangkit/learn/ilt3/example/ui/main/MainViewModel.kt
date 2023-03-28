package com.mufti.bangkit.learn.ilt3.example.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mufti.bangkit.learn.ilt3.example.data.mapper.UserMapper
import com.mufti.bangkit.learn.ilt3.example.data.response.UserResponse
import com.mufti.bangkit.learn.ilt3.example.data.retrofit.ApiConfig
import com.mufti.bangkit.learn.ilt3.example.model.User
import com.mufti.bangkit.learn.ilt3.example.data.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _listUser = MutableLiveData<Result<List<User>>>()
    val listUser: LiveData<Result<List<User>>> = _listUser

    fun getListUser() {
        val client = ApiConfig.getApiService().getListUsers("1")

        _listUser.value = Result.Loading
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    val dataResult = UserMapper.mapListUserResponseToListUser(result)

                    _listUser.value = Result.Success(dataResult)
                } else {
                    _listUser.value = Result.Error(response.message())
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                t.printStackTrace()
                _listUser.value = Result.Error(t.message.toString())
            }
        })
    }
}