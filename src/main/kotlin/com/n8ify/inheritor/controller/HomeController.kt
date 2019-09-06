package com.n8ify.inheritor.controller

import com.n8ify.inheritor.annotation.LogInputOutput
import com.n8ify.inheritor.annotation.ValidRole
import com.n8ify.inheritor.model.rest.request._base.BaseRequest
import com.n8ify.inheritor.model.rest.response._base.BaseResponse
import com.n8ify.inheritor.service.LoggerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
class HomeController {


    @Autowired
    lateinit var loggerService: LoggerService

    @GetMapping("/testError0")
    @LogInputOutput(asJsonInput = true, asJsonOutput = true)
    fun getSome(@Valid @RequestBody request : MRequest): MResponse {
        return MResponse(request.a, 10001)
    }

    class MRequest(@ValidRole val role : String, val a : String, val i : Int) : BaseRequest() {
        override fun toString(): String {
            return "MRequest(a='$a', i=$i)"
        }
    }
    class MResponse(val a : String, val o : Int) : BaseResponse() {
        override fun toString(): String {
            return "MResponse(a='$a', o=$o)"
        }
    }
}