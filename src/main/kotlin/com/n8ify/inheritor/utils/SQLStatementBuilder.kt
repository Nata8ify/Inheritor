package com.n8ify.inheritor.utils

import java.lang.StringBuilder

/** only single table is supported for now. */
class SQLStatementBuilder {

    private val statement by lazy { StringBuilder() }

    companion object {

        enum class LikeBehavior {
            LEFT_LIKE, RIGHT_LIKE, CENTER_LIKE
        }

        fun builder(): SQLStatementBuilder {
            return SQLStatementBuilder()
        }

    }


    private val INSERT = "INSERT "
    private val DELETE = "DELETE "
    private val UPDATE = "UPDATE "
    private val SELECT = "SELECT "

    private val FROM = " FROM "
    private val INNER_JOIN = " INNER_JOIN "
    private val LEFT_JOIN = " LEFT_JOIN "
    private val RIGHT_JOIN = " RIGHT_JOIN "


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
    private val LIKE = "LIKE"
    private val LIKE_SIGN = "%"
    private val BRACE_OPEN = " ("
    private val BRACE_CLOSE = ") "
    private val QOUTE_OPEN = " '"
    private val QOUTE_CLOSE = "' "
    private val SEMI_COLON = ";"

    private val TRUE_REPLACEMENT = "(1=1)"

    private val JOINER_TABLE_SUFFIX = "_table"
    private val JOINER_FIELD_SUFFIX = "_field"

    fun select(vararg fields: String): SQLStatementBuilder {
        this@SQLStatementBuilder.statement.append(SELECT)
        fields.forEachIndexed { index, field ->
            this@SQLStatementBuilder.statement.append(field)
            if (index.plus(1) != fields.size) {
                this@SQLStatementBuilder.statement.append(SEPARATOR)
            }
        }
        return this@SQLStatementBuilder
    }

    fun from(table: String): SQLStatementBuilder {
        this@SQLStatementBuilder.statement.append("$FROM$table")
        return this@SQLStatementBuilder
    }

    fun where(): SQLStatementBuilder {
        this@SQLStatementBuilder.statement.append(WHERE)
        return this@SQLStatementBuilder
    }

    fun equals(field: String, checkedValue: Any?, skipNull: Boolean = false): SQLStatementBuilder {

        if (skipNull && checkedValue == null) {
            this@SQLStatementBuilder.statement.append(TRUE_REPLACEMENT)
        } else {
            this@SQLStatementBuilder.statement.append("$field$EQUAL:$field")
        }

        return this@SQLStatementBuilder
    }

    fun like(field: String, behavior: LikeBehavior, checkedValue: Any?, skipNull: Boolean = false): SQLStatementBuilder {

        if (skipNull && checkedValue == null) {
            this@SQLStatementBuilder.statement.append(TRUE_REPLACEMENT)
        } else {

            when (behavior) {
                LikeBehavior.LEFT_LIKE -> this@SQLStatementBuilder.statement.append("$field$LIKE$LIKE_SIGN:$field")
                LikeBehavior.RIGHT_LIKE -> this@SQLStatementBuilder.statement.append("$field$LIKE:$field$LIKE_SIGN")
                LikeBehavior.CENTER_LIKE -> this@SQLStatementBuilder.statement.append("$field$LIKE$LIKE_SIGN:$field$LIKE_SIGN")
            }

        }

        return this@SQLStatementBuilder
    }

    fun and(): SQLStatementBuilder {
        this@SQLStatementBuilder.statement.append(AND)
        return this@SQLStatementBuilder
    }

    fun or(): SQLStatementBuilder {
        this@SQLStatementBuilder.statement.append(OR)
        return this@SQLStatementBuilder
    }

    fun not(): SQLStatementBuilder {
        this@SQLStatementBuilder.statement.append(NOT)
        return this@SQLStatementBuilder
    }

    fun _in(vararg args: String): SQLStatementBuilder {
        this@SQLStatementBuilder.statement.append(IN)
                .append(BRACE_OPEN)
        args.forEachIndexed { index, arg ->
            this@SQLStatementBuilder.statement.append(arg)
            if (index.plus(1) != args.size) {
                this@SQLStatementBuilder.statement.append(SEPARATOR)
            }
        }
        this@SQLStatementBuilder.statement.append(BRACE_CLOSE)
        return this@SQLStatementBuilder
    }

    fun greater(field: String, checkedValue: Any?, includeEqual: Boolean = false, skipNull: Boolean = false): SQLStatementBuilder {

        if (skipNull && checkedValue == null) {
            this@SQLStatementBuilder.statement.append(TRUE_REPLACEMENT)
        } else if (includeEqual) {
            this@SQLStatementBuilder.statement.append("$field$GREATER:$field")
        } else {
            this@SQLStatementBuilder.statement.append("$field$GREATER_THAN:$field")
        }

        return this@SQLStatementBuilder
    }

    fun less(field: String, checkedValue: Any?, includeEqual: Boolean = false, skipNull: Boolean = false): SQLStatementBuilder {

        if (skipNull && checkedValue == null) {
            this@SQLStatementBuilder.statement.append(TRUE_REPLACEMENT)
        } else if (includeEqual) {
            this@SQLStatementBuilder.statement.append("$field$LESS_THAN:$field")
        } else {
            this@SQLStatementBuilder.statement.append("$field$LESS:$field")
        }

        return this@SQLStatementBuilder
    }

    fun addPartly(sql: String): SQLStatementBuilder {
        this@SQLStatementBuilder.statement.append(" $sql ")
        return this@SQLStatementBuilder
    }

    fun buildQuery(): String {
        return this@SQLStatementBuilder.statement.append(SEMI_COLON).toString()
    }


}