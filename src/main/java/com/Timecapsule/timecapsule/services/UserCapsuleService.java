package com.Timecapsule.timecapsule.services;


import com.Timecapsule.timecapsule.dto.UserCapsulesDto.UserCapsuleDto;
import com.Timecapsule.timecapsule.exceptions.AppException;
import com.Timecapsule.timecapsule.models.Capsule;
import com.Timecapsule.timecapsule.models.User;
import com.Timecapsule.timecapsule.models.UserCapsule;
import com.Timecapsule.timecapsule.models.mappers.UserCapsuleMapper;
import com.Timecapsule.timecapsule.repository.CapsuleRepository;
import com.Timecapsule.timecapsule.repository.UserCapsuleRepository;
import com.Timecapsule.timecapsule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserCapsuleService {

    private final UserCapsuleRepository userCapsuleRepository;

    private final UserCapsuleMapper userCapsuleMapper;
    private final CapsuleRepository capsuleRepository;
    private final UserRepository userRepository;

    public List<UserCapsuleDto> getAll(){
        List<UserCapsule> userCapsules = userCapsuleRepository.findAll();

        return userCapsules.stream()
                .map(userCapsuleMapper::userCapsuleToDto)
                .collect(Collectors.toList());
    }

    public List<UserCapsuleDto> getByUserId(UUID userId){
        List<UserCapsule> userCapsules = userCapsuleRepository.findByUserId(userId);

        return userCapsules.stream()
                .map(userCapsuleMapper::userCapsuleToDto)
                .collect(Collectors.toList());
    }

    public UserCapsuleDto createUserCapsule(String email, UUID capsuleId){

        Capsule capsule = capsuleRepository.findById(capsuleId)
                .orElseThrow(() -> new AppException("Capsule not found", HttpStatus.NOT_FOUND));

        User user  = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        List<UserCapsule> userCapsules = userCapsuleRepository.findByCapsuleId(capsuleId);

        for (UserCapsule uc : userCapsules){
            if (uc.getUser().getId().equals(user.getId())){
                throw new AppException("The user is already added to this capsule", HttpStatus.BAD_REQUEST);
            }
        }

        UserCapsuleDto userCapsuleDto = new UserCapsuleDto(user.getId(), capsule.getId());

        UserCapsule userCapsule = userCapsuleMapper.createUserCapsule(userCapsuleDto);

        userCapsule.setCapsule(capsule);

        userCapsule.setUser(user);

        userCapsuleRepository.save(userCapsule);

        return userCapsuleMapper.userCapsuleToDto(userCapsule);
    }
}
