package com.n8ify.inheritor.service

import com.n8ify.inheritor.constant.LogConstant
import com.n8ify.inheritor.model.domain.RequestDescription
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.stereotype.Service
import java.lang.StringBuilder
import java.util.*

@Service
class LoggerService {

    val TAG = LoggerService::class.java.simpleName

    @Autowired
    lateinit var requestDescription: RequestDescription

    private val accessLogger by lazy { LoggerFactory.getLogger(LogConstant.TYPE_ACCESS) }
    private val systemLogger by lazy { LoggerFactory.getLogger(LogConstant.TYPE_SYSTEM) }
    private val queryLogger by lazy { LoggerFactory.getLogger(LogConstant.TYPE_QUERY) }
    private val externalLogger by lazy { LoggerFactory.getLogger(LogConstant.TYPE_EXTERNAL) }
    private val errorLogger by lazy { LoggerFactory.getLogger(LogConstant.TYPE_ERROR) }

    val ERROR_INVALID_LOG_LEVEL = "Invalid log level"

    /**
     * Logger for logging information on <b>"Presentation layer"</b> like <b>Controller</b>.
     * */
    fun accessLogger(tag: String, message: String, input: Any? = null, output: Any? = null, level: String, vararg remarks: Any) {

        // Step [1] : Create a log body.
        val body = StringBuilder().apply {

            val logId = requestDescription.id
            this@apply.append("[$logId :: $tag] >> ")
                    .append("[Message = $message] : ")
                    .append("[Input = $input] : ")
                    .append("[Output = $output]")

            if (remarks.isNotEmpty()) {
                this@apply.append("\n[Remark = ${Arrays.asList(*remarks)}]")
            }

        }.toString()

        // Step [2] : Log finale body by level.
        when (level) {

            LogConstant.LEVEL_TRACE -> accessLogger.trace(body)
            LogConstant.LEVEL_DEBUG -> accessLogger.debug(body)
            LogConstant.LEVEL_INFO -> accessLogger.info(body)
            LogConstant.LEVEL_WARN -> accessLogger.warn(body)
            LogConstant.LEVEL_ERROR -> accessLogger.error(body)

            else -> throw IllegalArgumentException("$ERROR_INVALID_LOG_LEVEL ($level)")

        }

    }

    /** Logger for logging information on <b>"Service layer"</b> or <b>"Data layer"</b>. Useful on
     * * Service layer (@Service)
     * * Data layer (@Repository) for non-query logging. */
    fun systemLogger(tag: String, message: String, result: Any? = null, level: String, vararg remarks: Any) {

        // Step [1] : Create a log body.
        val body = StringBuilder().apply {

            val logId = requestDescription.id
            this@apply.append("[$logId :: $tag] >> ")
                    .append("[Message = $message], ")
                    .append("[result = $result], ")

            if (remarks.isNotEmpty()) {
                this@apply.append("\n[Remark = ${Arrays.asList(*remarks)}]")
            }

        }.toString()

        // Step [2] : Log finale body by level.
        when (level) {

            LogConstant.LEVEL_TRACE -> systemLogger.trace(body)
            LogConstant.LEVEL_DEBUG -> systemLogger.debug(body)
            LogConstant.LEVEL_INFO -> systemLogger.info(body)
            LogConstant.LEVEL_WARN -> systemLogger.warn(body)
            LogConstant.LEVEL_ERROR -> systemLogger.error(body)

            else -> throw IllegalArgumentException("$ERROR_INVALID_LOG_LEVEL ($level)")

        }
    }

    fun queryLogger(tag: String, message: String, query: String, mapParams: MapSqlParameterSource? = null, listParams: List<Any?>? = null, level: String, vararg remarks: Any) {

        // Step [1] : Create a log body.
        val body = StringBuilder().apply {

            val logId = requestDescription.id
            this@apply.append("[$logId :: $tag] >> ")
                    .append("[Message = $message], ")
                    .append("[SQL = $query], ")
            this@apply.append("[Parameter(s) = ${mapParams?.toString() ?: listParams.toString()}], ")

            if (remarks.isNotEmpty()) {
                this@apply.append("\n[Remark = ${Arrays.asList(*remarks)}]")
            }

        }.toString()

        // Step [2] : Log finale body by level.
        when (level) {

            LogConstant.LEVEL_TRACE -> queryLogger.trace(body)
            LogConstant.LEVEL_DEBUG -> queryLogger.debug(body)
            LogConstant.LEVEL_INFO -> queryLogger.info(body)
            LogConstant.LEVEL_WARN -> queryLogger.warn(body)
            LogConstant.LEVEL_ERROR -> queryLogger.error(body)

            else -> throw IllegalArgumentException("$ERROR_INVALID_LOG_LEVEL ($level)")

        }
    }

    /**
     * Logger for logging information for an external API requesting.
     * */
    fun accessExternalLogger(tag: String, message: String, request: Any? = null, response: Any? = null, level: String, vararg remarks: Any) {

        // Step [1] : Create a log body.
        val body = StringBuilder().apply {

            val logId = requestDescription.id
            this@apply.append("[$logId :: $tag] >> ")
                    .append("[Message = $message] : ")

            if (request != null) {
                this@apply.append("[Request = $request] : ")
            }

            if (request != null) {
                this@apply..append("[Response = $response]")
            }

            if (remarks.isNotEmpty()) {
                this@apply.append("\n[Remark = ${Arrays.asList(*remarks)}]")
            }

        }.toString()

        // Step [2] : Log finale body by level.
        when (level) {

            LogConstant.LEVEL_TRACE -> accessLogger.trace(body)
            LogConstant.LEVEL_DEBUG -> accessLogger.debug(body)
            LogConstant.LEVEL_INFO -> accessLogger.info(body)
            LogConstant.LEVEL_WARN -> accessLogger.warn(body)
            LogConstant.LEVEL_ERROR -> accessLogger.error(body)

            else -> throw IllegalArgumentException("$ERROR_INVALID_LOG_LEVEL ($level)")

        }

    }

    /**
     * Logger for logging error information on <b>"Any layer"</b> which exception may thrown/occurred.
     * */
    fun printStacktraceErrorLogger(tag: String, message: String, throwable: Throwable, vararg remarks: Any) {

        // Step [1] : Create a log body.
        val body = StringBuilder().apply {

            val logId = requestDescription.id
            this@apply.append("[$logId :: $tag] >> ")
                    .append("[Message = $message], ")

            if (remarks.isNotEmpty()) {
                this@apply.append("\n[Remark = ${Arrays.asList(*remarks)}]")
            }

        }.toString()

        // Step [2] : Log finale body by level.
        errorLogger.error(body, throwable)


    }

}