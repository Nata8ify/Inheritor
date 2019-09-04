package com.n8ify.inheritor.model.rest.response._base

import com.n8ify.inheritor.constant.CharacterConstant
import java.util.*

data class ResponseInfo(val id : String, val status : String, val code : Int = 0, val message : String = CharacterConstant.EMPTY, val timestamp : Date = Date(System.currentTimeMillis())) {

    companion object {

        // -----  Status ------ //

        /** Anything is fine, Process flow is completely success. */
        const val STATUS_SUCCESS = "Success"

        /** Something wrong occurred on this process. But the process flow is done. */
        const val STATUS_FAIL = "Fail"

        /** Somethings causing fetal error, Process flow is undone */
        const val STATUS_ERROR = "Error"

        /** Default status. Process may be both of 'Success' or 'Fail' but just like the function didn't set it.  */
        const val STATUS_UNDEFINED = "Undefined"


        // -----  Code ------ //

        /** Default code which define the body which response back have nothing to worry */
        const val DEFAULT_CODE_NORMAL = 0

        /** Default code which define the body that response back should to worry but it's define as a global
         * (should replace with more specific code like <i>BaseException</i>'s code). */
        const val DEFAULT_CODE_ABNORMAL = -1
    }

}