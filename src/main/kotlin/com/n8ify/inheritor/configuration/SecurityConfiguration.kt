/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.n8ify.inheritor.configuration;

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
//@EnableWebSecurity
public class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        // TODO : Implement Somethings Here.
    }

    override fun configure(web: WebSecurity?) {
        // TODO : Implement Somethings Here.
    }

    override fun configure(http: HttpSecurity?) {
        // TODO : Implement Somethings Here.
    }

}
