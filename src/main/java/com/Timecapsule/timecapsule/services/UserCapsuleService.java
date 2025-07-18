package com.Timecapsule.timecapsule.services;


import com.Timecapsule.timecapsule.dto.UserCapsulesDto.UserCapsuleDto;
import com.Timecapsule.timecapsule.models.UserCapsule;
import com.Timecapsule.timecapsule.models.mappers.UserCapsuleMapper;
import com.Timecapsule.timecapsule.repository.UserCapsuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserCapsuleService {

    private final UserCapsuleRepository userCapsuleRepository;

    private final UserCapsuleMapper userCapsuleMapper;

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
}
