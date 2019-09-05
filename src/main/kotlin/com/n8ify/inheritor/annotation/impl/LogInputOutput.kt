package com.n8ify.inheritor.annotation.impl;

import com.fasterxml.jackson.databind.ObjectMapper
import com.n8ify.inheritor.annotation.LogInputOutput
import com.n8ify.inheritor.constant.LogLevel
import com.n8ify.inheritor.model.misc.RequestDescription
import com.n8ify.inheritor.model.rest.request._base.BaseRequest
import com.n8ify.inheritor.service.LoggerService
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.json.GsonJsonParser
import org.springframework.stereotype.Component

@Aspect
@Component
public class LogInputOutput {

    @Autowired
    lateinit var requestDescription: RequestDescription

    @Autowired
    lateinit var logger: LoggerService

    val jsonWriter by lazy { ObjectMapper().writer() }

    @Around("@annotation(com.n8ify.inheritor.annotation.LogInputOutput)")
    fun logInputOutput(joinPoint: ProceedingJoinPoint): Any {

        // Step [1] : Declare required attributes.
        val start = System.currentTimeMillis() // Note : For function time usage calculating purpose.
        val annotation = (joinPoint.signature as MethodSignature).method.getAnnotation(LogInputOutput::class.java) // Note : For accessing annotation metadata.


        // Step [2] : Iterate and log for each input which is a request (acknowledge by subtype of BaseRequest)
        joinPoint.args.forEach { arg ->
            if (arg is BaseRequest) {
                logger.accessLogger(joinPoint.signature.declaringType.simpleName, "Logging Input", input = if(annotation.asJsonInput){ jsonWriter.writeValueAsString(arg) } else {arg}, level = LogLevel.INFO)
            }
        }

        // Step [3] : Proceed a function's task as it normally would.
        val proceededObject = joinPoint.proceed()

        // Step [4] : Log a result of the proceeded task from [3].
        logger.accessLogger(joinPoint.signature.declaringType.simpleName, "Logging Output", output = if(annotation.asJsonOutput){ jsonWriter.writeValueAsString(proceededObject) } else {proceededObject}, level = LogLevel.INFO)

        // Step [5] : Calculate a finale proceeded time usage (in millisecond) and log it.
        val timeUsage =  System.currentTimeMillis().minus(start).toFloat().div(1_000)
        logger.systemLogger(joinPoint.signature.declaringType.simpleName , "<${requestDescription.request.servletPath}> Total time usages = $timeUsage milliseconds", level = LogLevel.INFO)

        // Step [6] : Return proceeded result.
        return proceededObject
    }

}
