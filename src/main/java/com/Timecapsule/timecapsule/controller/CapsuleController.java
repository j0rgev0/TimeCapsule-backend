package com.Timecapsule.timecapsule.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import com.Timecapsule.timecapsule.dto.*;
import com.Timecapsule.timecapsule.services.CapsuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/capsules")
public class CapsuleController {

    private final CapsuleService capsuleService;

    @GetMapping
    public ResponseEntity<List<CapsuleDto>> getAll() {
        List<CapsuleDto> capsules = capsuleService.getAll();
        return ResponseEntity.ok(capsules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCapsule(@PathVariable UUID id) {
        CapsuleDto capsule = capsuleService.getAll().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
        
        if (capsule == null) {
            return ResponseEntity.status(404).body(new ErrorDto("Capsule not Found"));
        }
        
        return ResponseEntity.ok(capsule);
    }

    @PostMapping
    public ResponseEntity<CapsuleDto> createCapsule(@Valid @RequestBody CreateCapsuleDto createCapsuleDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto currentUser = (UserDto) authentication.getPrincipal();
        

        CapsuleDto capsule = capsuleService.createCapsule(createCapsuleDto, currentUser.getId());

        return ResponseEntity.created(URI.create("/api/capsules/" + capsule.getId())).body(capsule);
    }
}