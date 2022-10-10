package com.heroappstrainee.githubrepository.data_layer.service.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.heroappstrainee.githubrepository.data_layer.model.response.UsersResponse

@Dao
interface UserDao {
    @Insert
    fun insertAll(vararg usersResponse: UsersResponse)

    @Delete
    fun delete(usersResponse: UsersResponse)

    @Query("SELECT * FROM user")
    fun getAll():List<UsersResponse>
}