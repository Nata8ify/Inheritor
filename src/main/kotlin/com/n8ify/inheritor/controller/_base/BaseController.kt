package com.n8ify.inheritor.controller._base

import com.n8ify.inheritor.service.LoggerService
import org.springframework.beans.factory.annotation.Autowired

/** Base class providing support component or convenient function for controller class. */
abstract class BaseController {

    /** Logger for logging things */
    @Autowired
    lateinit var logger : LoggerService

}