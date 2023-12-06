package com.example.tt.models.entities;

import com.example.tt.models.enums.Type;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Integer maxPeople;
    private Integer number;
    private Integer price;
    private Integer floor;
    @OneToOne
    private Group group;

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", type=" + type +
                ", maxPeople=" + maxPeople +
                ", number=" + number +
                ", price=" + price +
                ", floor=" + floor +
                '}';
    }
}
