package com.n8ify.inheritor

import com.n8ify.inheritor.constant.PropertiesConstant.BUILD_NAME
import com.n8ify.inheritor.constant.PropertiesConstant.BUILD_TIMESTAMP
import com.n8ify.inheritor.constant.PropertiesConstant.BUILD_VERSION
import com.n8ify.inheritor.constant.PropertiesConstant.PROFILES_ACTIVE
import com.n8ify.inheritor.model.domain.RequestDescription
import com.n8ify.inheritor.service.LoggerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InheritorApplication : CommandLineRunner {

    @Value(PROFILES_ACTIVE)
    lateinit var activeProfile : String

    @Value(BUILD_NAME)
    lateinit var buildName : String

    @Value(BUILD_VERSION)
    lateinit var buildVersion : String

    @Value(BUILD_TIMESTAMP)
    lateinit var buildTimestamp : String

    override fun run(vararg args: String?) {

        println("-----------------------------\n")
        println("\t$buildName ($activeProfile) $buildVersion")
        println("\t$buildTimestamp")
        println("\n-----------------------------")

    }

}

fun main(args: Array<String>) {
	runApplication<InheritorApplication>(*args)
}
