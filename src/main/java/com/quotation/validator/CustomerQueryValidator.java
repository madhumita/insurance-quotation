package com.quotation.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.quotation.model.CustomerQuery;

@Component
public class CustomerQueryValidator implements Validator {
	

	@Override
	public boolean supports(Class<?> clazz) {
		return CustomerQuery.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CustomerQuery query = (CustomerQuery) target;
		
		String name = query.getName();
		String postCode = query.getPostCode();
		String occupation = query.getOccupation();
		long minTurnover = query.getTurnOver();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Invalid Request:customer.name.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "postCode", "Invalid Request:customer.postCode.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "occupation", "Invalid Request:customer.occupation.empty");

		 		
		if (minTurnover < 1)
			errors.rejectValue("MINIMUM_TURNOVER", "Invalid Request:customer.minTurnover.invalid");

	}

}

