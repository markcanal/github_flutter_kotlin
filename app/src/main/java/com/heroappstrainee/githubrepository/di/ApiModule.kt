package com.heroappstrainee.githubrepository.di

import com.heroappstrainee.githubrepository.data_layer.repository.ApplicationRepository
import com.heroappstrainee.githubrepository.data_layer.service.remote.GithubApiService
import com.heroappstrainee.githubrepository.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())

            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit): GithubApiService =
        retrofit.create(GithubApiService::class.java)

    @Singleton
    @Provides
    fun provideRepository(githubApiService: GithubApiService) =
        ApplicationRepository(githubApiService)
}