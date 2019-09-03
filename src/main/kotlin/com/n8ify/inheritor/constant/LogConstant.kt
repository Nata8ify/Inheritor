package com.n8ify.inheritor.constant

enum class LogLevel {
    TRACE, DEBUG, INFO, WARN, ERROR, FETAL
}

object LogConstant {

    const val TYPE_ACCESS = "ACCESS"
    const val TYPE_SYSTEM = "SYSTEM"
    const val TYPE_QUERY = "QUERY"
    const val TYPE_EXTERNAL = "EXTERNAL"
    const val TYPE_ERROR = "ERROR"
}