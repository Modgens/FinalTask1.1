package com.example.tt.models.validation;

import com.example.tt.models.entities.Group;
import com.example.tt.models.entities.Visitant;
import com.example.tt.repositories.GroupRepo;
import com.example.tt.services.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class GroupValidator implements Validator {

    private final GroupRepo groupRepository;
    private final Calculator calculator;

    @Override
    public boolean supports(Class<?> clazz) {
        return Group.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Group group = (Group) target;

        List<Visitant> visitants = new ArrayList<>(group.getVisitants());
        visitants.add(group.getMainVisitant());

        Set<String> set = new HashSet<>();

        for (Visitant v: visitants) {
            if(groupRepository.doesVisitantHaveRoom(v.getPassportNumber())){
                errors.rejectValue("", "", "The visitant is already living in the hotel with password - " + v.getPassportNumber());
            }
            if(v.getPassportNumber()==null || v.getPassportNumber().isEmpty()) {
                errors.rejectValue("", "", "The password can not be empty" + v.getPassportNumber());
            }
            set.add(v.getPassportNumber());
        }
        if(set.size()!=visitants.size()) {
            errors.rejectValue("", "", "Visitant can not be main in room and guest in the same time");
        }
        if (calculator.getTotalDays(group.getDateIn(), group.getDateOut()) <= 0){
            errors.rejectValue("", "", "Group should live at least 1 day");
        }
    }
}