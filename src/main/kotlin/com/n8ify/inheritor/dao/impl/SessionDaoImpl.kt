package com.n8ify.inheritor.dao.impl;

import com.n8ify.inheritor.dao.SessionDao
import com.n8ify.inheritor.dao._base.BaseDao
import com.n8ify.inheritor.model.entity.Session
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.util.*

@Repository
public class SessionDaoImpl : BaseDao(), SessionDao {

    init {
        TAG = SessionDaoImpl::class.java.simpleName
    }

    private val TABLE_SESSION = "session"

    private val FIELD_SID = "sid"
    private val FIELD_STATUS = "status"
    private val FIELD_INIT_DATE = "init_date"
    private val FIELD_UPDATE_DATE = "update_date"
    private val FIELD_EXPIRE_DATE = "expire_time"
    private val FIELD_USER_ID = "user_id"

    override fun insertSession(session: Session): Int {

        val sql = StringBuilder().apply {
            this@apply.append(" INSERT INTO $TABLE_SESSION ($FIELD_SID, $FIELD_STATUS, $FIELD_INIT_DATE, $FIELD_UPDATE_DATE, $FIELD_EXPIRE_DATE, $FIELD_USER_ID)")
                    .append(" VALUES (:$FIELD_SID, :$FIELD_STATUS, :$FIELD_INIT_DATE, :$FIELD_UPDATE_DATE, :$FIELD_EXPIRE_DATE, :$FIELD_USER_ID) ")
        }.toString()

        val params = mapSqlParameterSourceOf(
                Pair(FIELD_SID, session.sid)
                , Pair(FIELD_STATUS, session.status)
                , Pair(FIELD_INIT_DATE, Date(System.currentTimeMillis()))
                , Pair(FIELD_UPDATE_DATE, Date(System.currentTimeMillis()))
                , Pair(FIELD_EXPIRE_DATE, session.expireDate)
                , Pair(FIELD_USER_ID, session.userId)
        )

        logQuery(TAG, "Insert a new session", sql, params)

        return namedParameterJdbcTemplate?.update(sql, params)
                ?: run { NO_RECORD_UPDATE }

    }

    override fun updateSessionStatusBySid(sid: String, status: String): Int {

        val sql = StringBuilder().apply {
            this@apply.append(" UPDATE $TABLE_SESSION ")
                    .append(" SET $FIELD_STATUS = :$FIELD_STATUS ")
                    .append(" WHERE $FIELD_SID = :$FIELD_SID ")
        }.toString()

        val params = mapSqlParameterSourceOf(
                Pair(FIELD_STATUS, status)
                , Pair(FIELD_SID, sid)
        )

        return namedParameterJdbcTemplate?.let { it.update(sql, params) } ?: run { NO_RECORD_UPDATE }
    }

    override fun inquirySessionById(sid: String): Session? {

        val sql = StringBuilder().apply {
            this@apply.append(" SELECT $FIELD_SID, $FIELD_STATUS, $FIELD_INIT_DATE, $FIELD_UPDATE_DATE, $FIELD_EXPIRE_DATE, $FIELD_USER_ID")
                    .append(" FROM $TABLE_SESSION")
                    .append(" WHERE $FIELD_SID = :$FIELD_SID")
        }.toString()

        val params = mapSqlParameterSourceOf(
                Pair(FIELD_SID, sid)
        )

        logQuery(TAG, "Inquiry a session by Id", sql, params)

        return namedParameterJdbcTemplate?.queryFirst(sql, params, RowMapper { rs, _ ->
            Session(
                    rs.getString(FIELD_SID)
                    , rs.getString(FIELD_STATUS)
                    , rs.getDate(FIELD_INIT_DATE)
                    , rs.getDate(FIELD_UPDATE_DATE)
                    , rs.getDate(FIELD_EXPIRE_DATE)
                    , rs.getInt(FIELD_USER_ID))
        })
    }
}
