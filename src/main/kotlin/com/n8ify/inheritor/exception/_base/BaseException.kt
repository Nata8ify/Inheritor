package com.n8ify.inheritor.exception._base

import com.n8ify.inheritor.constant.CharacterConstant
import com.n8ify.inheritor.model.rest.response.ResponseInfo

abstract class BaseException(val errorMessage : String = CharacterConstant.EMPTY, val errorCode : Int = ResponseInfo.DEFAULT_CODE_ABNORMAL) : Exception() {

}