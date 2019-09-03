package com.n8ify.inheritor.configuration

import com.n8ify.inheritor.constant.PropertiesConstant.REQUEST_DESCRIPTION_ID_PREFIX
import com.n8ify.inheritor.model.misc.RequestDescription
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import java.util.*
import javax.servlet.http.HttpServletRequest

@Configuration
class BeanConfiguration {

    private val logger = LoggerFactory.getLogger(BeanConfiguration::class.java)

    /** Provide a default bean using for identifying an incoming request or logging purpose.  */
    @Bean
    @Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    fun provideRequestDescription(@Value(REQUEST_DESCRIPTION_ID_PREFIX) prefix : String, request : HttpServletRequest) : RequestDescription {
        val id = "$prefix${System.currentTimeMillis()}${UUID.randomUUID()}"
        return RequestDescription().apply {
            this@apply.id = id
        }
    }

    /**
     * Let @JsonFormat know the time zone by using default system timezone.
     * @param objectMapper autowired objectMapper.
     */
    @Autowired
    fun setJsonFormatTimezone(objectMapper: com.fasterxml.jackson.databind.ObjectMapper) {
        objectMapper.setTimeZone(TimeZone.getDefault())
        logger.info("Default object mapper's time zone = ${TimeZone.getDefault()}")
    }

}