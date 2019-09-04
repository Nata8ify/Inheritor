package com.n8ify.inheritor.utils

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource

/** Alternatively builder class for generating <i>MapSqlParameterSource</i>.  */
class SQLParameterBuilder {


    companion object {

        /** Instantiate a new builder.
         * @param mapParams Initial parameter(s) as map */
        fun builder(mapParams : MutableMap<String, Any?> = mutableMapOf()) : SQLParameterBuilder {
            return SQLParameterBuilder().apply {
                this@apply.params = mapParams
            }
        }

    }

    lateinit var params : MutableMap<String, Any?>


    /** Add new parameter
     * @param fieldName field name which value will be placed.
     * @param fieldValue value of parameter.
     * @param skipNull validate <i>fieldValue<i> and avoid adding if it's null. */
    fun addParam(fieldName : String, fieldValue : Any?, skipNull : Boolean = false) : SQLParameterBuilder {

        if(skipNull && fieldValue == null){ return this@SQLParameterBuilder }

        this@SQLParameterBuilder.params[fieldName] = fieldValue
        return this@SQLParameterBuilder

    }

    /** Complete builder and generate instance of <i>MapSqlParameterSource</i> with values.
     * @return instance of <i>MapSqlParameterSource</i> with its values. */
    fun buildParams() : MapSqlParameterSource {
        return MapSqlParameterSource().apply {
            this@SQLParameterBuilder.params.forEach { entry ->
                this@apply.addValue(entry.key, entry.value)
            }
        }
    }

    protected class Builder(){


    }

}