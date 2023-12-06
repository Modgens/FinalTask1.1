package com.example.tt.models.dto;

import com.example.tt.models.dto.responses.VisitantResponse;
import com.example.tt.models.entities.Visitant;
import com.example.tt.repositories.GroupRepo;

public class Mapper {
    public static VisitantResponse mapVisitantToResponse(Visitant visitant, GroupRepo groupRepo){
        return VisitantResponse.builder()
                .fullName(visitant.getFirstName() + ' ' + visitant.getMiddleName() + ' ' + visitant.getLastName())
                .birthDate(visitant.getBirthDate())
                .passportNumber(visitant.getPassportNumber())
                .liveIn(groupRepo.findCurrentRoomByVisitant(visitant).orElse(0))
                .livedIn(groupRepo.findRoomsByVisitant(visitant))
                .build();
    }
}
