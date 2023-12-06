package com.example.tt.models.dto.responses;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface ShortVisitant {
    @Value("#{target.firstName + ' ' + target.lastName + ' ' + target.middleName}")
    String getFullName();
    String getPassportNumber();
}
