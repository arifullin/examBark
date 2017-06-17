package ru.kpfu.itis.validators;

import ru.kpfu.itis.model.forms.StudentProfileForm;
import ru.kpfu.itis.validators.annotations.FieldMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Safin Ramil on 08.05.17
 */
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    @Override
    public void initialize(FieldMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){

        if(obj instanceof StudentProfileForm){
            StudentProfileForm form = (StudentProfileForm) obj;
            return form.getPassword().equals(form.getPasswordRepeat()); // strictly equal
        }

        return false;
    }
}
