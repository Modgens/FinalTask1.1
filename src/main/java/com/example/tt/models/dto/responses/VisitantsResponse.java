package com.example.tt.models.dto.responses;

import java.util.Date;

public interface VisitantsResponse {
    Long getId();

    String getLastName() ;

    String getFirstName() ;

    String getMiddleName() ;

    Date getBirthDate() ;
}
