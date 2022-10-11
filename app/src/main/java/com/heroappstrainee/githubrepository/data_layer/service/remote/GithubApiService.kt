package com.heroappstrainee.githubrepository.data_layer.service.remote

import com.heroappstrainee.githubrepository.data_layer.model.response.UsersResponse
import com.heroappstrainee.githubrepository.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface GithubApiService {
    @GET(Constants.USER_END_POINT)
    suspend fun getAllUsers(): Response<List<UsersResponse>>
}