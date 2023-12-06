package com.example.tt.models.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Visitant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotBlank(message = "Middle name cannot be empty")
    private String middleName;

    @Pattern(regexp = "\\d{8}-\\d{4}", message = "Invalid passport number format. Use ########-####")
    @Column(unique = true)
    @NotBlank(message = "Passport number cannot be empty")
    private String passportNumber;

    private Date birthDate;
}
