package com.heroappstrainee.githubrepository.di

import android.content.Context
import com.heroappstrainee.githubrepository.data_layer.service.local.ApplicationDatabase
import com.heroappstrainee.githubrepository.data_layer.service.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): UserDao {
        return ApplicationDatabase.getInstance(context).userDao
    }
}