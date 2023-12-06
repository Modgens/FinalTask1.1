package com.example.tt.controllers;

import com.example.tt.services.GroupService;
import com.example.tt.services.PdfGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor
public class CheckController {

    private final PdfGenerator pdfGenerator;
    private final GroupService groupService;

    @GetMapping("/download/{groupId}")
    public ResponseEntity<InputStreamResource> downloadPdf(@PathVariable Long groupId) {

        ByteArrayInputStream bis = pdfGenerator.generatePdf(groupService.getGroupById(groupId));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=example.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}