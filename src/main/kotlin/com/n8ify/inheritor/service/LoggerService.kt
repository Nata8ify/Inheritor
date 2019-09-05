package com.n8ify.inheritor.service

import com.n8ify.inheritor.constant.LogConstant
import com.n8ify.inheritor.constant.LogLevel
import com.n8ify.inheritor.constant.LogLevel.*
import com.n8ify.inheritor.model.misc.RequestDescription
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.stereotype.Service
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
    fun accessLogger(tag: String, message: String, input: Any? = null, output: Any? = null, level: LogLevel, vararg remarks: Any) {

        // Step [1] : Create a log body.
        val body = StringBuilder().apply {

            val logId = requestDescription.id
            this@apply.append("[$logId::$tag][${requestDescription.request.servletPath}] >> ")
                    .append("[Message = $message]")

            input?.let {
                this@apply.append(", [Input = $it]")
            }

            output?.let {
                this@apply.append(", [Output = $it]")
            }
            if(output != null){
            }

            if (remarks.isNotEmpty()) {
                this@apply.append("\n[Remark = ${Arrays.asList(*remarks)}]")
            }

        }.toString()

        // Step [2] : Log finale body by level.
        when (level) {

            TRACE -> accessLogger.trace(body)
            DEBUG -> accessLogger.debug(body)
            INFO -> accessLogger.info(body)
            WARN -> accessLogger.warn(body)
            ERROR -> accessLogger.error(body)

            else -> throw IllegalArgumentException("$ERROR_INVALID_LOG_LEVEL ($level)")

        }

    }

    /** Logger for logging information on <b>"Service layer"</b> or <b>"Data layer"</b>. Useful on
     * * Service layer (@Service)
     * * Data layer (@Repository) for non-query logging. */
    fun systemLogger(tag: String, message: String, result: Any? = null, level: LogLevel, vararg remarks: Any) {

        // Step [1] : Create a log body.
        val body = StringBuilder().apply {

            val logId = requestDescription.id
            this@apply.append("[$logId::$tag] >> ")
                    .append("[Message = $message]")

            result?.let {
                this@apply.append(", [Result = $result]")
            }

            if (remarks.isNotEmpty()) {
                this@apply.append("\n[Remark = ${Arrays.asList(*remarks)}]")
            }

        }.toString()

        // Step [2] : Log finale body by level.
        when (level) {

            TRACE -> systemLogger.trace(body)
            DEBUG -> systemLogger.debug(body)
            INFO -> systemLogger.info(body)
            WARN -> systemLogger.warn(body)
            ERROR -> systemLogger.error(body)

            else -> throw IllegalArgumentException("$ERROR_INVALID_LOG_LEVEL ($level)")

        }
    }

    fun queryLogger(tag: String, message: String, query: String, mapParams: MapSqlParameterSource? = null, level: LogLevel, vararg remarks: Any) {

        // Step [1] : Create a log body.
        val body = StringBuilder().apply {

            val logId = requestDescription.id
            this@apply.append("[$logId::$tag] >> ")
                    .append("[Message = $message]")
                    .append(", [SQL = $query]")
            this@apply.append(", [Parameter(s) = ${mapParams?.values.toString()}]")

            if (remarks.isNotEmpty()) {
                this@apply.append("\n[Remark = ${Arrays.asList(*remarks)}]")
            }

        }.toString()

        // Step [2] : Log finale body by level.
        when (level) {

            TRACE -> queryLogger.trace(body)
            DEBUG -> queryLogger.debug(body)
            INFO -> queryLogger.info(body)
            WARN -> queryLogger.warn(body)
            ERROR -> queryLogger.error(body)

            else -> throw IllegalArgumentException("$ERROR_INVALID_LOG_LEVEL ($level)")

        }
    }

    /**
     * Logger for logging information for an external API requesting.
     * */
    fun accessExternalLogger(tag: String, message: String, request: Any? = null, response: Any? = null, level: LogLevel, vararg remarks: Any) {

        // Step [1] : Create a log body.
        val body = StringBuilder().apply {

            val logId = requestDescription.id
            this@apply.append("[$logId::$tag] >> ")
                    .append("[Message = $message]")

            request?.let {
                this@apply.append(", [Request = $it]")
            }


            response?.let {
                this@apply.append(", [Response = $it]")
            }

            if (remarks.isNotEmpty()) {
                this@apply.append("\n[Remark = ${Arrays.asList(*remarks)}]")
            }

        }.toString()

        // Step [2] : Log finale body by level.
        when (level) {

            TRACE -> externalLogger.trace(body)
            DEBUG -> externalLogger.debug(body)
            INFO -> externalLogger.info(body)
            WARN -> externalLogger.warn(body)
            ERROR -> externalLogger.error(body)

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
            this@apply.append("[$logId::$tag] >> ")
                    .append("[Message = $message], ")

            if (remarks.isNotEmpty()) {
                this@apply.append("\n[Remark = ${Arrays.asList(*remarks)}]")
            }

        }.toString()

        // Step [2] : Log finale body by level.
        errorLogger.error(body, throwable)


    }

}