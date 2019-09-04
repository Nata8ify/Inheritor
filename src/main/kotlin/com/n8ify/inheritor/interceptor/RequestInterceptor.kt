package com.n8ify.inheritor.interceptor

import com.n8ify.inheritor.constant.LogLevel
import com.n8ify.inheritor.interceptor._base.BaseInterceptor
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class RequestInterceptor : BaseInterceptor() {

    val TAG = RequestInterceptor::class.java.simpleName

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.systemLogger(TAG, "Pre-handler", request.servletPath, LogLevel.INFO)
        return super.preHandle(request, response, handler)
    }

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        super.postHandle(request, response, handler, modelAndView)
    }

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        logger.systemLogger(TAG, "Post-handler", request.servletPath, LogLevel.INFO)
        super.afterCompletion(request, response, handler, ex)
    }
}