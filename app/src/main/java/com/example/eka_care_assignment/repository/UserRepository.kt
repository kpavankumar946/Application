package com.example.eka_care_assignment.repository

import androidx.lifecycle.LiveData
import com.example.eka_care_assignment.database.UserDao
import com.example.eka_care_assignment.model.User

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }
}


