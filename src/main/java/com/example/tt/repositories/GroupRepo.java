package com.example.tt.repositories;

import com.example.tt.models.dto.responses.HistoryResponse;
import com.example.tt.models.entities.Group;
import com.example.tt.models.entities.Visitant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface GroupRepo extends JpaRepository<Group, Long> {

    @Query("SELECT g.room.number FROM Group g WHERE g.mainVisitant = :visitant OR :visitant MEMBER OF g.visitants")
    Set<Integer> findRoomsByVisitant(@Param("visitant") Visitant visitant);

    @Query("SELECT g.room.number FROM Group g WHERE (g.mainVisitant = :visitant OR :visitant MEMBER OF g.visitants) and g.room.group IS NOT NULL")
    Optional<Integer> findCurrentRoomByVisitant(@Param("visitant") Visitant visitant);

    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN true ELSE false END FROM Group g JOIN g.visitants v WHERE (g.mainVisitant.passportNumber = :passportId OR v.passportNumber = :passportId) AND g.room.group IS NOT NULL")
    boolean doesVisitantHaveRoom(@Param("passportId") String passportId);

    List<HistoryResponse> findAllBy();
}
