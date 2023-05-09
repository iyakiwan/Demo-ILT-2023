package com.mufti.bangkit.learn.ilt3.example.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mufti.bangkit.learn.ilt3.example.data.Result
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mufti.bangkit.learn.ilt3.example.databinding.ActivityMainBinding
import com.mufti.bangkit.learn.ilt3.example.ui.MapsActivity
import com.mufti.bangkit.learn.ilt3.example.utils.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)

        viewModel = ViewModelProvider(
            this,
            factory
        )[MainViewModel::class.java]

        initialView()
        setupRecyclerView()

        observerListUser()
    }

    private fun initialView() {
        binding.fabMaps.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter()

        binding.rvUsers.setHasFixedSize(true)
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        binding.rvUsers.adapter = adapter
    }

    private fun observerListUser() {
        viewModel.listUser.observe(this){
            adapter.submitData(lifecycle, it)
            /*when(it){
                is Result.Loading -> {
                    binding.pvUsers.isVisible = true
                }
                is Result.Success -> {
                    binding.pvUsers.isVisible = false
                    adapter.refreshData(it.data)
                }
                is Result.Error -> {
                    binding.pvUsers.isVisible = false
                    Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                }
            }*/
        }
    }
}