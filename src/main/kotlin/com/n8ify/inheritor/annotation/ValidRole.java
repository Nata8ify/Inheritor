package com.n8ify.inheritor.annotation;

import com.n8ify.inheritor.constant.RoleConstant;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidRole.ValidRoleImpl.class)
public @interface ValidRole {
    String message() default "Invalid Role";
    Class<?>[] groups()  default {};
    Class<? extends Payload>[] payload() default {};

    class ValidRoleImpl implements ConstraintValidator<ValidRole, String> {

        private List<String> ROLES = Arrays.asList(RoleConstant.ROLE_ADMIN, RoleConstant.ROLE_USER, RoleConstant.ROLE_ANONYMOUS, RoleConstant.ROLE_DOPPELGÄNGER);

        @Override
        public boolean isValid(String role, ConstraintValidatorContext context) {
            return ROLES.contains(role);
        }

        @Override
        public void initialize(ValidRole constraintAnnotation) {

        }

    }

}

