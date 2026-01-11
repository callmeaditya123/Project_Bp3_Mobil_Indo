package com.pab.project_bp3_mobil_indo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val email: String,
    val password: String
)
