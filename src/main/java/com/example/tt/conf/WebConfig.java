package com.example.tt.conf;

import com.example.tt.services.Calculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);
    }
    @Bean
    public Calculator calculator() {
        return new Calculator() {
            @Override
            public Integer getTotalAmount(Date dateIn, Date dateOut, Integer price) {
                if (price<=0)
                    throw new IllegalArgumentException();
                return getTotalDays(dateIn, dateOut) * price;
            }

            @Override
            public Integer getTotalDays(Date dateIn, Date dateOut) {
                // Перетворюємо java.util.Date в java.time.LocalDate
                LocalDate dateInLD = dateIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate dateOutLD = dateOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                // Розрахунок тривалості між датами
                Duration duration = Duration.between(dateInLD.atStartOfDay(), dateOutLD.atStartOfDay());

                // Повертаємо кількість днів
                return Math.toIntExact(duration.toDays());
            }
        };
    }
}
