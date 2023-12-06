package com.example.tt.controllers;

import com.example.tt.models.dto.responses.HistoryResponse;
import com.example.tt.models.entities.Group;
import com.example.tt.models.validation.GroupValidator;
import com.example.tt.models.validation.VisitantValidator;
import com.example.tt.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final GroupValidator groupValidator;

    @InitBinder("group")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(groupValidator);
    }

    @GetMapping
    public ResponseEntity<List<HistoryResponse>> getAllGroups() {
        List<HistoryResponse> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable Long id) {
        Group group = groupService.getGroupById(id);
        return ResponseEntity.ok(group);
    }

    @PostMapping
    public ResponseEntity<Void> createGroup(@Validated @RequestBody Group group) {
        groupService.createGroup(group);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGroup(@PathVariable Long id, @Validated @RequestBody Group group) {
        groupService.updateGroupDateOut(id, group);
        return ResponseEntity.noContent().build();
    }
}
