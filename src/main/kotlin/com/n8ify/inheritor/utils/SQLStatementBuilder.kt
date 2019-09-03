package com.n8ify.inheritor.utils

import java.lang.StringBuilder

class SQLStatementBuilder {

    private val statement by lazy { StringBuilder() }

    companion object {

        fun builder() : SQLStatementBuilder {
            return SQLStatementBuilder()
        }

    }


    private val INSERT = "INSERT "
    private val DELETE = "DELETE "
    private val UPDATE = "UPDATE "
    private val SELECT = "SELECT "

    private val FROM = " FROM "
    private val WHERE = " WHERE "
    private val ORDER_BY = " ORDER BY "
    private val ASC = " ASC "
    private val DESC = " DESC "

    private val NOT = " NOT "
    private val IN = " IN "
    private val AND = " AND  "
    private val OR = " OR "

    private val SEPARATOR = ", "
    private val EQUAL = " = "
    private val GREATER = " > "
    private val GREATER_THAN = " >= "
    private val LESS = " < "
    private val LESS_THAN = " <= "
    private val LIKE = "%"
    private val BRACE_OPEN = " ("
    private val BRACE_CLOSE = ") "
    private val QOUTE_OPEN = " '"
    private val QOUTE_CLOSE = "' "
    private val SEMI_COLON = ";"

    private val TRUE_REPLACEMENT = "(1=1)"

    fun select(vararg fieldNames : String) : SQLStatementBuilder {
        this@SQLStatementBuilder.statement.append(SELECT)
        fieldNames.forEachIndexed { index, fieldName ->
            this@SQLStatementBuilder.statement.append(fieldName)
            if(index.plus(1) != fieldNames.size){
                this@SQLStatementBuilder.statement.append(SEPARATOR)
            }
        }
        return this@SQLStatementBuilder
    }

    fun from(table : String) : SQLStatementBuilder {
        this@SQLStatementBuilder.statement.append("$FROM$table")
        return this@SQLStatementBuilder
    }

    fun where() : SQLStatementBuilder {
        this@SQLStatementBuilder.statement.append(WHERE)
        return this@SQLStatementBuilder
    }

    fun equals(fieldName : String, fieldValue : Any?, skipNull : Boolean = false) : SQLStatementBuilder {

        if(skipNull && fieldValue == null){
            this@SQLStatementBuilder.statement.append(TRUE_REPLACEMENT)
        } else {
            this@SQLStatementBuilder.statement.append("$fieldName$EQUAL:$fieldName")
        }

        return this@SQLStatementBuilder
    }

    fun addPartly(sql : String) : SQLStatementBuilder {
        this@SQLStatementBuilder.statement.append(" $sql ")
        return this@SQLStatementBuilder
    }

    fun buildQuery() : String {
        return this@SQLStatementBuilder.statement.append(SEMI_COLON).toString()
    }

}