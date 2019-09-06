package com.n8ify.inheritor.model.entity

import java.util.*

data class Session(val sid : String, val status : String, val initDate : Date, val updateDate : Date, val expireDate : Date, val userId : Int)