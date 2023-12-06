package com.example.tt.controllers;

import com.example.tt.models.dto.responses.VisitantResponse;
import com.example.tt.models.entities.Visitant;
import com.example.tt.models.validation.VisitantValidator;
import com.example.tt.services.VisitantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/visitants")
public class VisitantController {

    private final VisitantService visitantService;
    private final VisitantValidator visitantValidator;

    @InitBinder("visitant")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(visitantValidator);
    }

    @GetMapping
    public ResponseEntity<Page<Visitant>> getAllVisitants(
            @RequestParam(required = false) String searchTerm,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Visitant> visitantsPage = visitantService.getAllVisitants(searchTerm, PageRequest.of(page, size));
        return ResponseEntity.ok(visitantsPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitantResponse> getVisitantById(@PathVariable Long id) {
        VisitantResponse visitant = visitantService.getVisitantById(id);
        return ResponseEntity.ok(visitant);
    }

    @PostMapping
    public ResponseEntity<Void> createVisitant(@Validated @RequestBody Visitant visitant) {
        visitantService.createVisitant(visitant);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
