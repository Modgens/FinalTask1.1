package com.example.tt.controllers;

import com.example.tt.models.dto.responses.RoomResponse;
import com.example.tt.models.dto.responses.RoomsResponse;
import com.example.tt.models.entities.Room;
import com.example.tt.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomsResponse>> getAllRooms(@RequestParam(required = false) String filterBy) {
        List<RoomsResponse> rooms = roomService.getAllRooms(filterBy);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable Long id) {
        RoomResponse room = roomService.getRoomById(id);
        return ResponseEntity.ok(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRoom(@PathVariable Long id) {
        roomService.evictGroupFromRoom(id);
        return ResponseEntity.noContent().build();
    }
}
