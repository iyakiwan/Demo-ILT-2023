package com.mufti.bangkit.learn.ilt3.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mufti.bangkit.learn.ilt3.example.databinding.ActivityMainBinding
import com.mufti.bangkit.learn.ilt3.example.model.DataDummy

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
        adapter.refreshData(DataDummy.dataDummyUser())
    }
}