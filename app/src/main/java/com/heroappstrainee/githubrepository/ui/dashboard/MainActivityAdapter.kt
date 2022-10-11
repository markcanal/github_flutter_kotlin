package com.heroappstrainee.githubrepository.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.heroappstrainee.githubrepository.data_layer.model.entity.UserEntity
import com.heroappstrainee.githubrepository.databinding.UserCollectionItemBinding
import javax.inject.Inject

class MainActivityAdapter @Inject constructor() :
    RecyclerView.Adapter<MainActivityAdapter.ViewModel>() {
    var usersList: List<UserEntity> = emptyList()

    inner class ViewModel(private val binding: UserCollectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(users: UserEntity) {
            binding.apply {
                users.also { (login, avatar, url) ->
                    userLogin.text = login
                    userHtml.text = url
                    Glide.with(binding.root).load(avatar).circleCrop().into(userAvatar)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewModel =
        ViewModel(
            UserCollectionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ViewModel, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount(): Int = usersList.size

}