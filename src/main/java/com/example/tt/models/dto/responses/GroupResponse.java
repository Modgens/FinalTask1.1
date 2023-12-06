package com.example.tt.models.dto.responses;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;

public interface GroupResponse {
    Long getId();
    @Value("#{target.mainVisitant}")
    ShortVisitant getMainVisitant();
    @Value("#{target.visitants}")
    List<ShortVisitant> getVisitants();
    Date getDateIn();
    Date getDateOut();
}
