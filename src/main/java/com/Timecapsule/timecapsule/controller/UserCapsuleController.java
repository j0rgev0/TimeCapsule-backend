package com.Timecapsule.timecapsule.controller;


import com.Timecapsule.timecapsule.dto.UserCapsulesDto.AddUserCapsuleDto;
import com.Timecapsule.timecapsule.dto.UserCapsulesDto.UserCapsuleDto;
import com.Timecapsule.timecapsule.services.UserCapsuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<?> createUserCapsule(@RequestBody AddUserCapsuleDto addUserCapsuleDto){
        try{

            UserCapsuleDto userCapsuleDto = userCapsuleService.createUserCapsule(addUserCapsuleDto.getEmail(), addUserCapsuleDto.getCapsuleId());

            return ResponseEntity.ok(userCapsuleDto);
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    @DeleteMapping("/{capsuleId}")
    public ResponseEntity<?> deleteUserCapsule(@PathVariable UUID capsuleId, @RequestParam String email){
        try{
            userCapsuleService.deleteUserCapsule(capsuleId, email);
            return ResponseEntity.ok("User Capsule deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
