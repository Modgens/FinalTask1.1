package com.example.tt.models.dto.responses;

import com.example.tt.models.entities.Visitant;
import org.springframework.beans.factory.annotation.Value;
import java.time.Duration;

import java.util.Date;
import java.util.List;

public interface HistoryResponse {
    Long getId();
    Date getDateIn();
    Date getDateOut();
    @Value("#{target.room.number}")
    Integer getNumber();
    @Value("#{@calculator.getTotalAmount(target.dateIn, target.dateOut, target.room.price)}")
    Integer getTotalAmount();
    ShortVisitant getMainVisitant();
    List<ShortVisitant> getVisitants();
}
