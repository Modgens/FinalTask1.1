package com.example.tt.models.dto.requestes;

import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class GroupRequest {
    private Long id;
    private Date dateIn;
    private Date dateOut;
    private Long roomId;
    private Long mainVisitant;
    private List<Long> visitants;
}
