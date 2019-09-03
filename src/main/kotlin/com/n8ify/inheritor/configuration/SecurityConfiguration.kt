package com.n8ify.inheritor.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {


    override fun configure(auth: AuthenticationManagerBuilder?) {
        // TODO : Implement Things.
    }

    override fun configure(web: WebSecurity?) {
        // TODO : Implement Things.
    }

    override fun configure(http: HttpSecurity?) {
        // TODO : Implement Things.
    }
}