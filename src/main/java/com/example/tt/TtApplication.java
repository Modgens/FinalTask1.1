package com.example.tt;

import com.example.tt.models.dto.responses.RoomResponse;
import com.example.tt.models.dto.responses.ShortVisitant;
import com.example.tt.models.entities.Group;
import com.example.tt.models.entities.Room;
import com.example.tt.models.entities.Visitant;
import com.example.tt.models.enums.Type;
import com.example.tt.repositories.GroupRepo;
import com.example.tt.repositories.RoomRepo;
import com.example.tt.repositories.VisitantRepo;
import com.example.tt.services.PdfGenerator;
import com.github.javafaker.Faker;
import org.aspectj.runtime.reflect.Factory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.crypto.Data;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class TtApplication {

    public static void main(String[] args) {
        SpringApplication.run(TtApplication.class, args);
    }
    @Bean
    public CommandLineRunner myCommandLineRunner(VisitantRepo visitantRepo) {
        return args -> {
        };
    }

}
