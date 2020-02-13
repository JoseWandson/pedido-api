package com.wandson.api.exceptionhandler;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wandson.api.model.Pedido;

public class MaxSizeConstraintValidator implements ConstraintValidator<MaxSizeConstraint, List<Pedido>> {

	@Override
	public boolean isValid(List<Pedido> values, ConstraintValidatorContext context) {
		return values.size() <= 10;
	}

}
