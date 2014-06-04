/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author thomas
 */
public class NummerplaatValidator implements ConstraintValidator<ValidNummerplaat, String>{

    @Override
    public void initialize(ValidNummerplaat constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("[A-Z]{3}-[0-9]{3}");
    }
    
}
