package com.n8ify.inheritor.dao

import com.n8ify.inheritor.model.entity.User

interface UserDao {

    fun findUserByUsername(username : String) : User?

}