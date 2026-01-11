package com.pab.project_bp3_mobil_indo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    fun login(email: String, password: String): UserEntity?

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    fun checkEmail(email: String): UserEntity?

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    fun getUserByEmail(email: String): UserEntity?
}
