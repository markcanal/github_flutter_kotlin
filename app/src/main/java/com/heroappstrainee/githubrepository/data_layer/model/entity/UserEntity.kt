package com.heroappstrainee.githubrepository.data_layer.model.entity

data class UserEntity(
    val login: String,
    val avatar: String,
    val url: String
){
    fun clear(){
        UserEntity("","","")
    }
}
