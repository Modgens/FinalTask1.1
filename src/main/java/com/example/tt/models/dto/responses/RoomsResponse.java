package com.example.tt.models.dto.responses;

import com.example.tt.models.enums.Type;
import org.springframework.beans.factory.annotation.Value;

public interface RoomsResponse {
    Long getId();
    Type getType();
    Integer getMaxPeople();
    Integer getNumber();
    Integer getPrice();
    Integer getFloor();

    @Value("#{target.group == null ? true : false }")
    Boolean getIsAvailable();
}
