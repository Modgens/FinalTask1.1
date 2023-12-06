package com.example.tt.repositories;

import com.example.tt.models.dto.responses.RoomResponse;
import com.example.tt.models.dto.responses.RoomsResponse;
import com.example.tt.models.entities.Room;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepo extends JpaRepository<Room, Long> {
    List<RoomsResponse> findAllBy();
    Optional<RoomResponse> findProjectedById(Long id);

    @Query("SELECT r FROM Room r WHERE FUNCTION('DATEDIFF', r.group.dateOut, CURRENT_DATE) = 0")
    List<RoomsResponse> findAllByFilterToday();

    @Query("SELECT r FROM Room r WHERE FUNCTION('DATEDIFF', r.group.dateOut, CURRENT_DATE) <=3 AND FUNCTION('DATEDIFF', r.group.dateOut, CURRENT_DATE) >0")
    List<RoomsResponse> findAllByFilterLessThanThreeDays();

    @Query("SELECT r FROM Room r WHERE FUNCTION('DATEDIFF', r.group.dateOut, CURRENT_DATE) < 0")
    List<RoomsResponse> findAllByFilterDelay();
}
