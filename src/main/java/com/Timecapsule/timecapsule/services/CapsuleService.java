package com.Timecapsule.timecapsule.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.Timecapsule.timecapsule.dto.CapsulesDtos.CapsuleDto;
import com.Timecapsule.timecapsule.dto.CapsulesDtos.CreateCapsuleDto;
import com.Timecapsule.timecapsule.dto.CapsulesDtos.UpdateCapsuleDto;
import com.Timecapsule.timecapsule.exceptions.AppException;
import com.Timecapsule.timecapsule.models.Capsule;
import com.Timecapsule.timecapsule.models.User;
import com.Timecapsule.timecapsule.models.mappers.CapsuleMapper;
import com.Timecapsule.timecapsule.repository.CapsuleRepository;
import com.Timecapsule.timecapsule.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CapsuleService {

    private final CapsuleRepository capsuleRepository;

    private final UserRepository userRepository;

    private final CapsuleMapper capsuleMapper;


    public List<CapsuleDto> getAll() {
        List<Capsule> capsules = capsuleRepository.findAll();
        return capsules.stream()
                .map(capsuleMapper::toCapsuleDto)
                .collect(Collectors.toList());
    }

    public CapsuleDto getCapsule(UUID id) {
        Capsule capsule = capsuleRepository.findById(id)
                .orElseThrow(() -> new AppException("Capsule not found", HttpStatus.NOT_FOUND));

        return capsuleMapper.toCapsuleDto(capsule);
    }

    public CapsuleDto createCapsule(CreateCapsuleDto capsuleDto, UUID id){
        Optional<Capsule> optionalCapsule = capsuleRepository.findByTitle(capsuleDto.getTitle());

        if (optionalCapsule.isPresent()){
            throw new AppException("Capsule already exists", HttpStatus.BAD_REQUEST);
        }

        User owner = userRepository.findById(id)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        Capsule capsule = capsuleMapper.createCapsule(capsuleDto);

        capsule.setOwner(owner);

        Capsule saveCapsule = capsuleRepository.save(capsule);

        return capsuleMapper.toCapsuleDto(saveCapsule);
    }

    public CapsuleDto updateCapsule(UUID id, UpdateCapsuleDto capsuleDto) {
        Capsule existingCapsule = capsuleRepository.findById(id)
                .orElseThrow(() -> new AppException("Capsule not found", HttpStatus.NOT_FOUND));

        if (capsuleDto.getTitle() != null && !capsuleDto.getTitle().trim().isEmpty()) {
            Optional<Capsule> optionalCapsule = capsuleRepository.findByTitle(capsuleDto.getTitle());
            if (optionalCapsule.isPresent() && !optionalCapsule.get().getId().equals(id)) {
                throw new AppException("Another capsule with this title already exists", HttpStatus.BAD_REQUEST);
            }
        }

        capsuleMapper.updateCapsuleFromDto(capsuleDto, existingCapsule);

        Capsule savedCapsule = capsuleRepository.save(existingCapsule);

        return capsuleMapper.toCapsuleDto(savedCapsule);
    }
}