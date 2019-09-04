package com.n8ify.inheritor.configuration

import com.n8ify.inheritor.interceptor.RequestInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class MvcConfiguration : WebMvcConfigurer {

    @Autowired
    lateinit var requestInterceptor: RequestInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(requestInterceptor)
    }

}