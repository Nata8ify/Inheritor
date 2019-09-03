package com.n8ify.inheritor.dao._base

import com.n8ify.inheritor.service.LoggerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport
import javax.annotation.PostConstruct
import javax.sql.DataSource

/** Base class providing support component or convenient function for DAO class. */
abstract class BaseDao : NamedParameterJdbcDaoSupport() {

    /** Initial a data source for any time the bean have been instantiated. */
    @Autowired
    @PostConstruct
    fun doOnPostConstruct(mDataSource : DataSource){
        setDataSource(mDataSource)
    }

    /** Logger for logging things */
    @Autowired
    lateinit var logger : LoggerService

}