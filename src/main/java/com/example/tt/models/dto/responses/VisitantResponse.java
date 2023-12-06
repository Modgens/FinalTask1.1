package com.example.tt.models.dto.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
public class VisitantResponse {
    private String fullName;
    private Date birthDate;
    private String passportNumber;
    private Set<Integer> livedIn;
    private Integer liveIn;
}

/*
Пробував все ж проекцію до кінця, але не вийшло, ніяк не хоче підтягуватись репозиторій
public interface VisitantResponse {
    @Value("#{target.firstName + ' ' + target.lastName + ' ' + target.middleName}")
    String getFullName();
    Date getBirthDate();
    String getPassportNumber();
    @Value("#{@groupRepo.findRoomsByVisitant(target.id)}")
    List<Integer> livedIn();
    @Value("#{@groupRepo.findCurrentRoomByVisitant(target.id).orElse(\"Не проживає зараз\")}")
    String liveIn();
}
 */