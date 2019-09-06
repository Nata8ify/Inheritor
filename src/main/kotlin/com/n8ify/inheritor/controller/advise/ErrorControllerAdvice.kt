package com.n8ify.inheritor.controller.advise

import com.n8ify.inheritor.exception._base.BaseException
import com.n8ify.inheritor.model.misc.RequestDescription
import com.n8ify.inheritor.model.rest.response.ResponseInfo
import com.n8ify.inheritor.service.LoggerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Conceptual Credit : Anuwat.k
 *
 * */

@ControllerAdvice
class ErrorControllerAdvice {

    private val TAG = ErrorControllerAdvice::class.java.simpleName

    @Autowired
    lateinit var requestDescription: RequestDescription

    @Autowired
    lateinit var logger: LoggerService

    /** Default exception handler for global java exception (global exception not produced by custom <i>BaseException</i>.) */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception::class)
    fun handlerException(e: Exception): ResponseInfo {
        return generateErrorResponseInfo(e)
    }

    /** Error response info generating function for (global) exception which not produced by custom <i>BaseException</i>. */
    fun generateErrorResponseInfo(e: Exception): ResponseInfo {
        e.printStackTrace()
        return ResponseInfo(
                requestDescription.id
                , ResponseInfo.STATUS_ERROR
                , ResponseInfo.DEFAULT_CODE_ABNORMAL
                , e.message ?: e.localizedMessage)
    }


    /** Error response info generating function for (global) exception which produced by custom <i>BaseException</i>. */
    fun generateBaseErrorResponseInfo(be: BaseException): ResponseInfo {
        be.printStackTrace()
        return ResponseInfo(
                requestDescription.id
                , ResponseInfo.STATUS_ERROR
                , be.errorCode
                , be.errorMessage)
    }
}