package com.example.tt.models.dto.responses;

import com.example.tt.models.enums.Type;
import org.springframework.beans.factory.annotation.Value;
import java.util.Date;
import java.util.List;

public interface RoomResponse {
    Long getId();
    Type getType();
    Integer getMaxPeople();
    Integer getNumber();
    Integer getPrice();
    Integer getFloor();
    @Value("#{target.group == null}")
    Boolean getIsAvailable();
    @Value("#{target.group}")
    GroupResponse getGroup();
}
