package com.example.tt;


import com.example.tt.models.entities.Room;
import com.example.tt.models.enums.Type;
import com.example.tt.repositories.RoomRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class TtApplication {

    public static void main(String[] args) {
        SpringApplication.run(TtApplication.class, args);
    }

    /*
    @Bean
    public CommandLineRunner CommandLineRunnerBean(RoomRepo repo) {
        return (args) -> {
            List<Room> rooms = new ArrayList<>();

            Type[] types = Type.values();

            for (int i = 1; i <= 50; i++) {
                Room room = new Room();
                room.setType(types[i % types.length]); // Циклічне призначення типів кімнат
                room.setMaxPeople(ThreadLocalRandom.current().nextInt(1, 6)); // Випадкове число від 1 до 5 включно
                room.setNumber(i); // Номер кімнати
                room.setPrice(100 * i); // Ціна (наприклад, 100 * номер кімнати)
                room.setFloor((i - 1) / 10 + 1); // Поверх (наприклад, кожні 10 кімнат на одному поверсі)
                room.setGroup(null); // Ви можете встановити групу відповідно до ваших потреб

                rooms.add(room);
            }

            repo.saveAll(rooms);
        };
    }
    */

}
