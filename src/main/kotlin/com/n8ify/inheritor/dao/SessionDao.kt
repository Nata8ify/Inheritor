package com.n8ify.inheritor.dao

import com.n8ify.inheritor.model.entity.Session

interface SessionDao {

    fun insertSession(session : Session) : Int
    fun updateSession(session : Session) : Int
    fun inquirySessionById(sid : String) : Session?

}