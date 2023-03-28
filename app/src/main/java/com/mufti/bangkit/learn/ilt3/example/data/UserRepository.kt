package com.mufti.bangkit.learn.ilt3.example.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.mufti.bangkit.learn.ilt3.example.data.local.room.UserDao
import com.mufti.bangkit.learn.ilt3.example.data.remote.retrofit.ApiService
import com.mufti.bangkit.learn.ilt3.example.model.User
import com.mufti.bangkit.learn.ilt3.example.data.remote.mapper.UserMapper

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) {

    fun getListUser(): LiveData<Result<List<User>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getListUsers("1")
            val dataResult = UserMapper.mapListUserResponseToListUserEntity(response)

            userDao.deleteAllUser()

            userDao.insertUser(dataResult)

            emit(Result.Success(UserMapper.mapListUserEntityToListUser(userDao.getAllUser())))
        } catch (e: Exception) {
            Log.d("UserRepository", "getListUser: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
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