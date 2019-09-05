package com.n8ify.inheritor.controller

import com.n8ify.inheritor.annotation.LogInputOutput
import com.n8ify.inheritor.model.rest.request._base.BaseRequest
import com.n8ify.inheritor.model.rest.response._base.BaseResponse
import com.n8ify.inheritor.service.LoggerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {


    @Autowired
    lateinit var loggerService: LoggerService

    @GetMapping("/testError0")
    @LogInputOutput(asJsonInput = true, asJsonOutput = false)
    fun getSome(@RequestBody request : MRequest): MResponse {
        return MResponse(request.a, 10001)
    }

    class MRequest(val a : String, val i : Int) : BaseRequest() {
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