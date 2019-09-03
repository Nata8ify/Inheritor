package com.n8ify.inheritor.utils

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource

class SQLParameterBuilder {


    companion object {

        fun builder(mapParams : MutableMap<String, Any?> = mutableMapOf()) : SQLParameterBuilder {
            return SQLParameterBuilder().apply {
                this@apply.params = mapParams
            }
        }

    }

    lateinit var params : MutableMap<String, Any?>


    fun addParam(fieldName : String, fieldValue : Any?, skipNull : Boolean = false) : SQLParameterBuilder {

        if(skipNull && fieldValue == null){ return this@SQLParameterBuilder }

        this@SQLParameterBuilder.params[fieldName] = fieldValue
        return this@SQLParameterBuilder

    }

    fun buildParams() : MapSqlParameterSource {
        return MapSqlParameterSource().apply {
            this@SQLParameterBuilder.params.forEach { entry ->
                this@apply.addValue(entry.key, entry.value)
            }
        }
    }

}