package com.mufti.bangkit.learn.ilt3.example.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.mufti.bangkit.learn.ilt3.example.R
import com.mufti.bangkit.learn.ilt3.example.databinding.ItemListUserBinding
import com.mufti.bangkit.learn.ilt3.example.model.User

class UserAdapter:
    PagingDataAdapter<User, UserAdapter.ListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class ListViewHolder(private var binding: ItemListUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            user: User
        ) {
            Glide.with(binding.root)
                .load(user.avatar)
                .apply(RequestOptions().override(80, 80).placeholder(R.drawable.icon_avatar))
                .transform(CircleCrop())
                .into(binding.itemAvatar)

            binding.itemName.text = binding.root.context.getString(R.string.format_full_name, user.firstName, user.lastName)
            binding.itemEmail.text = user.email
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}