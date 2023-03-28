package com.mufti.bangkit.learn.ilt3.example

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.mufti.bangkit.learn.ilt3.example.databinding.ItemListUserBinding
import com.mufti.bangkit.learn.ilt3.example.model.User

class UserAdapter(private var users: List<User>) :
    RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
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

    fun refreshData(users: List<User>) {
        val diffResult = DiffUtil.calculateDiff(diffCallback(this.users, users))

        this.users = users
        diffResult.dispatchUpdatesTo(this)
    }

    private fun diffCallback(
        oldList: List<User>,
        newList: List<User>
    ) = object : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] === newList[newItemPosition]
    }
}