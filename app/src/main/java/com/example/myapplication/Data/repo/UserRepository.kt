package com.example.myapplication.Data.repo

import com.example.myapplication.Data.local.dao.UserDao
import com.example.myapplication.Data.model.user

class UserRepository(private val userDao: UserDao) {
    suspend fun getUser(username: String): user? {
        return userDao.getUser(username)
    }
    suspend fun insertUser(user: user) {
        userDao.insertUser(user)
    }
}