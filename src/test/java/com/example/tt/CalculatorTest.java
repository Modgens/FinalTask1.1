package com.example.tt;

import com.example.tt.services.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
    Серед бізнес-логіки на тестування підходив лише Калькулятор, тож на жаль це все що я міг протестувати
 */

@SpringBootTest
public class    CalculatorTest {

    @Autowired
    private Calculator calculator;

    @Test
    public void testGetTotalAmount() {
        Date dateIn = new Date();
        Date dateOut = new Date();
        Integer price = 10;

        Integer totalAmount = calculator.getTotalAmount(dateIn, dateOut, price);

        assertEquals(Integer.valueOf(0), totalAmount);
    }

    @Test
    public void testGetTotalDays() {
        LocalDate dateInLD = LocalDate.now();
        LocalDate dateOutLD = LocalDate.now().plusDays(5);

        Date dateIn = Date.from(dateInLD.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateOut = Date.from(dateOutLD.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Integer totalDays = calculator.getTotalDays(dateIn, dateOut);

        assertEquals(Integer.valueOf(5), totalDays);
    }

    @Test
    public void testGetTotalDaysWithSameDate() {
        LocalDate dateInLD = LocalDate.now();
        LocalDate dateOutLD = LocalDate.now();

        Date dateIn = Date.from(dateInLD.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateOut = Date.from(dateOutLD.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Integer totalDays = calculator.getTotalDays(dateIn, dateOut);

        assertEquals(Integer.valueOf(0), totalDays);
    }

    @Test
    public void testGetTotalDaysWithNullDates() {
        assertThrows(NullPointerException.class, () -> calculator.getTotalDays(null, null));
    }

    @Test
    public void testGetTotalAmountWithNegativePrice() {
        Date dateIn = new Date();
        Date dateOut = new Date();
        Integer price = -10;

        assertThrows(IllegalArgumentException.class, () -> calculator.getTotalAmount(dateIn, dateOut, price));
    }
}
