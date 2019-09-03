package com.n8ify.inheritor.controller

import com.n8ify.inheritor.service.LoggerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {


    @Autowired
    lateinit var loggerService: LoggerService

    @GetMapping("/testError0")
    fun getSome(): Int {
        try {
            return 9 / 0
        } catch (e: Exception) {
            loggerService.printStacktraceErrorLogger("", "Error By Divide Zer0", e, "Force divide by zero")
        }
        return 1010
    }
}