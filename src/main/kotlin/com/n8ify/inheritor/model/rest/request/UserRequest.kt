package com.n8ify.inheritor.model.rest.request

import com.n8ify.inheritor.annotation.ValidRole
import javax.validation.constraints.Max

data class UserRequest(
        val userId : Int,
        val username : String,
        val password : String,
        val deviceId : String?,
        @ValidRole val role : String) {
}