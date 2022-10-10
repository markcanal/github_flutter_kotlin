package com.heroappstrainee.githubrepository.data_layer.repository

import com.heroappstrainee.githubrepository.data_layer.service.remote.GithubApiService

class ApplicationRepository(private  val  githubApiService: GithubApiService) {
    suspend fun getAllUsers() = githubApiService.getAllUsers()
}