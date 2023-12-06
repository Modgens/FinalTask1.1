package com.example.tt.models.validation;

import com.example.tt.models.entities.Group;
import com.example.tt.models.entities.Visitant;
import com.example.tt.repositories.VisitantRepo;
import com.example.tt.services.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class VisitantValidator implements Validator {

    private final VisitantRepo visitantRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Visitant.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Visitant visitant = (Visitant) target;

        // Перевірка на відсутність значення (null)
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passportNumber", "field.required", "Passport number is required");

        // Перевірка на унікальність номера паспорта
        if (visitant.getPassportNumber() != null && visitantRepository.existsByPassportNumber(visitant.getPassportNumber())) {
            errors.rejectValue("passportNumber", "unique.passportNumber", "Passport number must be unique");
        }
    }
}
