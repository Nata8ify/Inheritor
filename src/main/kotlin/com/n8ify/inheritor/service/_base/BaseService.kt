package com.n8ify.inheritor.service._base

import com.n8ify.inheritor.service.LoggerService
import org.springframework.beans.factory.annotation.Autowired

/** Base class providing support component or convenient function for service class. */
abstract class BaseService {

    protected var TAG = BaseService::class.java.simpleName


    @Autowired
    lateinit var logger: LoggerService

}