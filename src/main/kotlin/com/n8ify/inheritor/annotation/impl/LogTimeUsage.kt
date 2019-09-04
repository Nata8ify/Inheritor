package com.n8ify.inheritor.annotation.impl

import com.n8ify.inheritor.constant.LogLevel.*
import com.n8ify.inheritor.model.misc.RequestDescription
import com.n8ify.inheritor.service.LoggerService
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Aspect
@Component
class LogTimeUsage {

    @Autowired
    lateinit var logger : LoggerService

    @Autowired
    lateinit var requestDescription: RequestDescription

    @Around("@annotation(com.n8ify.inheritor.annotation.LogTimeUsage)")
    fun logTimeUsage(proceedingJoinPoint: ProceedingJoinPoint) : Any? {

        val start = System.currentTimeMillis()

        val proceededObject = proceedingJoinPoint.proceed()

        val timeUsage =  System.currentTimeMillis().minus(start).toFloat().div(1000)

        logger.systemLogger(proceedingJoinPoint.signature.declaringType.simpleName , "[${requestDescription.request.servletPath}] Total time usages = $timeUsage milliseconds", level = INFO)

        return proceededObject
    }

}