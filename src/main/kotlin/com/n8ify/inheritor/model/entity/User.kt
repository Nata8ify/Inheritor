package com.n8ify.inheritor.model.entity

data class User(val userId : Int, val username : String, val password : String, val deviceId : String?, val role : String)