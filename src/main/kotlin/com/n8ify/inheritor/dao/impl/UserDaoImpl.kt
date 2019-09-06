package com.n8ify.inheritor.dao.impl

import com.n8ify.inheritor.constant.LogLevel
import com.n8ify.inheritor.dao.UserDao
import com.n8ify.inheritor.dao._base.BaseDao
import com.n8ify.inheritor.model.entity.User
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.stereotype.Repository

@Repository
class UserDaoImpl : BaseDao(), UserDao {

    private val FIELD_USERID = "user_id"
    private val FIELD_USERNAME = "username"
    private val FIELD_PASSWORD = "password"
    private val FIELD_DEVICE_ID = "device_id"
    private val FIELD_ROLE = "role"
    private val TABLE_USER = "user"

    override fun findUserByUsername(username: String): User? {

        val sql = StringBuilder().apply {
            this@apply.append("SELECT $FIELD_USERID, $FIELD_USERNAME, $FIELD_PASSWORD, $FIELD_DEVICE_ID, $FIELD_ROLE")
                    .append(" FROM $TABLE_USER")
                    .append(" WHERE $FIELD_USERNAME = :$FIELD_USERNAME")
        }.toString()

        val params = MapSqlParameterSource().apply {
            this@apply.addValue(FIELD_USERNAME, username)
        }

        loggerService.queryLogger(TAG, "findUserByUsername", sql, params, LogLevel.INFO)

        return namedParameterJdbcTemplate?.queryFirst(sql, params, RowMapper<User> { rs, _ ->
            return@RowMapper User(rs.getInt(FIELD_USERID)
                    , rs.getString(FIELD_USERNAME)
                    , rs.getString(FIELD_PASSWORD)
                    , rs.getString(FIELD_DEVICE_ID)
                    , rs.getString(FIELD_ROLE))
        })

    }

}