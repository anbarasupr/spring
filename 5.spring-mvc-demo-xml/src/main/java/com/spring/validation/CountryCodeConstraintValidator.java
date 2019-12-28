package com.spring.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountryCodeConstraintValidator implements ConstraintValidator<CountryCode, String> {

	private String[] countries;

	@Override
	public void initialize(CountryCode theCountryCode) {
		countries = theCountryCode.value();
	}

	@Override
	public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContext) {

		boolean result = false;

		if (theCode != null) {

			//
			// loop thru course prefixes
			//
			// check to see if code matches any of the course prefixes
			//
			for (String country : countries) {
				result = theCode.equalsIgnoreCase(country);

				// if we found a match then break out of the loop
				if (result) { 
					break;
				}
			}
		} else {
			result = false;
		}

		return result;
	}
}
