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
public class SerienummerValidator implements ConstraintValidator<ValidSerienummer, String>{

    @Override
    public void initialize(ValidSerienummer constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches("[0-9]{3}-[0-9]{3}-[0-9]{3}");
    }
    
}
