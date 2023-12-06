package com.example.tt.repositories;

import com.example.tt.models.dto.responses.ShortVisitant;
import com.example.tt.models.dto.responses.VisitantResponse;
import com.example.tt.models.entities.Visitant;
import com.example.tt.repositories.specifications.VisitantSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VisitantRepo extends JpaRepository<Visitant, Long>, JpaSpecificationExecutor<Visitant> {
    Optional<Visitant> findVisitantByPassportNumber(String passportNumber);

    boolean existsByPassportNumber(String passportNumber);
}
