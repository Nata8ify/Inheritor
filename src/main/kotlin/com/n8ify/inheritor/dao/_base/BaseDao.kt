package com.n8ify.inheritor.dao._base

import com.n8ify.inheritor.constant.LogLevel.*
import com.n8ify.inheritor.service.LoggerService
import com.n8ify.inheritor.utils.SQLParameterBuilder
import com.n8ify.inheritor.utils.SQLStatementBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.annotation.PostConstruct
import javax.sql.DataSource

/** Base class providing support component or convenient function for DAO class. */
abstract class BaseDao : NamedParameterJdbcDaoSupport() {

    var TAG = BaseDao::class.java.simpleName

    @Autowired
    lateinit var mDataSource: DataSource

    /** Initial a data source for any time the bean have been instantiated. */
    @PostConstruct
    fun doOnPostConstruct() {
        setDataSource(mDataSource)
    }

    /** Logger for logging things (Name <i>"logger"</i> is already adopted by its super class) */
    @Autowired
    lateinit var loggerService: LoggerService


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

    fun SQLStatementBuilder.createQuery() : String = this@createQuery.buildQuery()

    fun SQLParameterBuilder.createParams() : MapSqlParameterSource = this@createParams.buildParams()

}
