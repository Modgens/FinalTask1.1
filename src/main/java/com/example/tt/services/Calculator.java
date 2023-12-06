package com.example.tt.services;

import java.util.Date;

public interface Calculator {
    Integer getTotalAmount(Date dateIn, Date dateOut, Integer price);
    Integer getTotalDays(Date dateIn, Date dateOut);
}
