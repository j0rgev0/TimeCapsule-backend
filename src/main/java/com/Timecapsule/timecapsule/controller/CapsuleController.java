package com.Timecapsule.timecapsule.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.Timecapsule.timecapsule.dto.CapsulesDtos.CapsuleDto;
import com.Timecapsule.timecapsule.dto.CapsulesDtos.CreateCapsuleDto;
import com.Timecapsule.timecapsule.dto.CapsulesDtos.UpdateCapsuleDto;
import com.Timecapsule.timecapsule.dto.ErrorDto;
import com.Timecapsule.timecapsule.dto.UserDto;
import com.Timecapsule.timecapsule.exceptions.AppException;
import com.Timecapsule.timecapsule.services.CapsuleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
        try {
            CapsuleDto capsule = capsuleService.getCapsule(id);
            return ResponseEntity.ok(capsule);
        } catch (AppException e) {
            return ResponseEntity.status(404).body(new ErrorDto("Capsule not Found"));
        }
    }

    @PostMapping
    public ResponseEntity<CapsuleDto> createCapsule(@Valid @RequestBody CreateCapsuleDto createCapsuleDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto currentUser = (UserDto) authentication.getPrincipal();

        CapsuleDto capsule = capsuleService.createCapsule(createCapsuleDto, currentUser.getId());

        return ResponseEntity.created(URI.create("/api/capsules/" + capsule.getId())).body(capsule);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCapsule(@PathVariable UUID id, @RequestBody UpdateCapsuleDto capsuleDto) {
        try {
            CapsuleDto updatedCapsule = capsuleService.updateCapsule(id, capsuleDto);
            return ResponseEntity.ok(updatedCapsule);
        } catch (AppException e) {
            return ResponseEntity.status(e.getCode()).body(new ErrorDto(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCapsule(@PathVariable UUID id) {
        try {
            capsuleService.deleteCapsule(id);
            
            return ResponseEntity.ok("Capsule deleted successfully");
        } catch (AppException e) {
            return ResponseEntity.status(e.getCode()).body(new ErrorDto(e.getMessage()));
        }

    }
}