package com.example.tt.services;

import com.example.tt.models.dto.responses.RoomResponse;
import com.example.tt.models.dto.responses.RoomsResponse;
import com.example.tt.models.entities.Room;
import com.example.tt.repositories.RoomRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepo roomRepository;

    public List<RoomsResponse> getAllRooms(String filterType) {

        if (filterType == null || filterType.isEmpty()) {
            return roomRepository.findAllBy();
        }

        return switch (filterType) {
            case "delayed" -> roomRepository.findAllByFilterDelay();
            case "today" -> roomRepository.findAllByFilterToday();
            case "lessThanThreeDays" -> roomRepository.findAllByFilterLessThanThreeDays();
            default -> roomRepository.findAllBy();
        };
    }

    public RoomResponse getRoomById(Long id) {
        Optional<RoomResponse> optionalRoom = roomRepository.findProjectedById(id);
        return optionalRoom.orElse(null);
    }

    @Transactional
    public void evictGroupFromRoom(Long id) {
        Optional<Room> originalRoom = roomRepository.findById(id);
        if (originalRoom.isPresent()) {
            originalRoom.get().setGroup(null);
            roomRepository.save(originalRoom.get());
        }
    }
}
