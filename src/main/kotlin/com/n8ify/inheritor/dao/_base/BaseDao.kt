package com.n8ify.inheritor.dao._base

import com.n8ify.inheritor.constant.LogLevel
import com.n8ify.inheritor.constant.LogLevel.*
import com.n8ify.inheritor.service.LoggerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.*
import javax.annotation.PostConstruct
import javax.sql.DataSource

/** Base class providing support component or convenient function for DAO class. */
abstract class BaseDao : NamedParameterJdbcDaoSupport() {

    protected var TAG = BaseDao::class.java.simpleName

    @Autowired
    lateinit var mDataSource: DataSource

    protected val NO_RECORD_UPDATE by lazy { 0 }

    protected val currentDate: Date
        get() = Date(System.currentTimeMillis())

    /** Initial a data source for any time the bean have been instantiated. */
    @PostConstruct
    fun doOnPostConstruct() {
        setDataSource(mDataSource)
    }

    /** Logger for logging things (Name <i>"logger"</i> is already adopted by its super class) */
    @Autowired
    lateinit var loggerService: LoggerService


    /** Convenient Function **/
    /** Generate <i>MapSqlParameterSource</i> through convenient map shorthand function.
     */
    fun mapSqlParameterSourceOf(vararg params: Pair<String, Any?>, mustNotNull : Boolean = false): MapSqlParameterSource {

        if(mustNotNull){
            params.iterator().forEach {
                if(it.second == null){

                }
            }
        }

        return MapSqlParameterSource().apply {
            params.forEach { param ->
                this@apply.addValue(param.first, param.second)
            }
        }
    }

    /** Convenient (Extension) Function **/
    /** Custom extension of <i>namedParameterJdbcTemplate.query(sql, params, mapper)</i> which always returns the first member from its result.
     * @param sql SQL Statement
     * @param params Statement parameters
     * @param mapper Row mapper for mapping result set object into java object.
     * @return The first member of its result set. */
    fun <T> NamedParameterJdbcTemplate.queryFirst(sql: String, params: MapSqlParameterSource, mapper: RowMapper<T>): T? {

        return namedParameterJdbcTemplate?.let { jdbcTemplate ->

            return@let jdbcTemplate.query(sql, params, mapper).let { result ->
                return@let if (result.isNotEmpty()) {
                    result.first()
                } else {
                    loggerService.systemLogger(TAG, "Empty result", result, INFO)
                    null
                }
            }

        } ?: run {
            loggerService.systemLogger(TAG, "Named JDBC template is not available", namedParameterJdbcTemplate, WARN)
            return@run null
        }

    }

    /* Miscellenious Function */
    /** Helper method for log query statement and its parameter(s). */
    fun logQuery(tag: String, message: String, sql: String, params: MapSqlParameterSource, level: LogLevel = DEBUG, vararg remark: String) {
        loggerService.queryLogger(tag, message, sql, params, level, remark)
    }

}
