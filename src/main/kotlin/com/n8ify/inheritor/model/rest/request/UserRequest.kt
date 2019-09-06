package com.n8ify.inheritor.model.rest.request

import com.n8ify.inheritor.annotation.ValidRole

data class UserRequest(
        val userId : Int,
        val username : String,
        val password : String,
        val deviceId : String?,
        val role : String) {
}