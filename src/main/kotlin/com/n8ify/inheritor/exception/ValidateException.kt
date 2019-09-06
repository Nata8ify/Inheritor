package com.n8ify.inheritor.exception

import com.n8ify.inheritor.exception._base.BaseException

/**
 * Conceptual Credit : Anuwat.k
 * Fetal exception caused by validating attribute is violate validation criterion.
 * */
class ValidateException(errorMessage : String, errorCode : Int) : BaseException(errorMessage, errorCode) {
}