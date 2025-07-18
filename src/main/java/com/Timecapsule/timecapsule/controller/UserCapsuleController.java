package com.Timecapsule.timecapsule.controller;


import com.Timecapsule.timecapsule.dto.UserCapsulesDto.UserCapsuleDto;
import com.Timecapsule.timecapsule.models.UserCapsule;
import com.Timecapsule.timecapsule.services.UserCapsuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/userCapsule")
public class UserCapsuleController {

    private final UserCapsuleService userCapsuleService;

    @GetMapping
    public ResponseEntity<List<UserCapsuleDto>> getAllUserCapsules(){
        List<UserCapsuleDto> userCapsule = userCapsuleService.getAll();
        return ResponseEntity.ok(userCapsule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<UserCapsuleDto>> getByUserId(@PathVariable UUID id){
        List<UserCapsuleDto> userCapsule = userCapsuleService.getByUserId(id);

        return ResponseEntity.ok(userCapsule);
    }
}
