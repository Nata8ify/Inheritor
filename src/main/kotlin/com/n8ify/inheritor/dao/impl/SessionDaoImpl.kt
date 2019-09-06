package com.n8ify.inheritor.dao.impl;

import com.n8ify.inheritor.dao.SessionDao
import com.n8ify.inheritor.dao._base.BaseDao
import com.n8ify.inheritor.model.entity.Session
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.stereotype.Repository;
import java.lang.StringBuilder

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateSession(session: Session): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun inquirySessionById(sid: String): Session? {
        val sql = StringBuilder().apply {
            this@apply.append("SELECT $FIELD_SID, $FIELD_STATUS, $FIELD_INIT_DATE, $FIELD_UPDATE_DATE, $FIELD_EXPIRE_DATE, $FIELD_USER_ID")
                    .append("FROM $TABLE_SESSION")
                    .append("WHERE $FIELD_SID = :$FIELD_SID")
        }.toString()

        val params = mapSqlParameterSourceOf(
                Pair(FIELD_SID, sid)
        )

        return namedParameterJdbcTemplate?.queryFirst(sql, params, RowMapper { rs, rn ->
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
