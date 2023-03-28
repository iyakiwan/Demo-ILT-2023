package com.mufti.bangkit.learn.ilt3.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.mufti.bangkit.learn.ilt3.example.data.mapper.UserMapper
import com.mufti.bangkit.learn.ilt3.example.data.response.UserResponse
import com.mufti.bangkit.learn.ilt3.example.data.retrofit.ApiConfig
import com.mufti.bangkit.learn.ilt3.example.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        getUser()
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter(mutableListOf())

        binding.rvUsers.setHasFixedSize(true)
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter
    }

    private fun getUser() {
        val client = ApiConfig.getApiService().getListUsers("1")

        binding.pvUsers.isVisible = true
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    val dataResult = UserMapper.mapListUserResponseToListUser(result)

                    adapter.refreshData(dataResult)
                }
                binding.pvUsers.isVisible = false
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
                binding.pvUsers.isVisible = false
            }
        })
    }
}