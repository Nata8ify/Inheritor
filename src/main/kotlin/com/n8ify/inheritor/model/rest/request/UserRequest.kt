package com.n8ify.inheritor.model.rest.request

data class UserRequest(
        val userId : Int,
        val username : String,
        val password : String,
        val deviceId : String?,
        val role : String) {
}