package com.example.tt.services;

import com.example.tt.models.dto.Mapper;
import com.example.tt.models.dto.responses.ShortVisitant;
import com.example.tt.models.dto.responses.VisitantResponse;
import com.example.tt.models.entities.Visitant;
import com.example.tt.repositories.GroupRepo;
import com.example.tt.repositories.VisitantRepo;
import com.example.tt.repositories.specifications.VisitantSpecifications;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitantService {

    private final VisitantRepo visitantRepository;
    private final GroupRepo groupRepo;

    public Page<Visitant> getAllVisitants(String searchTerm, Pageable pageable) {
        Specification<Visitant> spec = VisitantSpecifications.buildSpecification(searchTerm);
        return visitantRepository.findAll(spec, pageable);
    }


    public Visitant getByPassportId(String password) {
        return visitantRepository.findVisitantByPassportNumber(password).orElseThrow(()->
                new EntityNotFoundException("Visitant not found with password: " + password));
    }

    public VisitantResponse getVisitantById(Long id) {
        return Mapper.mapVisitantToResponse(visitantRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Visitant not found with id: " + id)), groupRepo);
    }

    public void createVisitant(Visitant visitant) {
        visitantRepository.save(visitant);
    }
}
