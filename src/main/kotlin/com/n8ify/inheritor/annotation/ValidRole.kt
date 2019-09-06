package com.n8ify.inheritor.annotation;

import com.n8ify.inheritor.constant.RoleConstant
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@kotlin.annotation.Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [ValidRoleImpl::class])
annotation class ValidRole(val message : String = "Invalid Role", val groups : Array<KClass<*>> = [], val payload : Array<KClass<in Payload>> = [])

class ValidRoleImpl() : ConstraintValidator<ValidRole, String> {

    val ROLES = arrayOf(RoleConstant.ROLE_ADMIN, RoleConstant.ROLE_USER, RoleConstant.ROLE_ANONYMOUS, RoleConstant.ROLE_DOPPELGÄNGER)

    override fun isValid(role : String , context : ConstraintValidatorContext) : Boolean{
        return ROLES.contains(role);
    }

    @Override
    override fun initialize(constraintAnnotation : ValidRole) : Unit {

    }

}

