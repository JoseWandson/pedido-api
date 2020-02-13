package com.wandson.api.exceptionhandler;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = MaxSizeConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxSizeConstraint {

	String message() default "Não pode haver mais de 10 pedidos";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
