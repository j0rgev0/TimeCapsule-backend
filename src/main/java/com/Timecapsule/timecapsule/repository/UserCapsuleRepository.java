package com.Timecapsule.timecapsule.repository;

import com.Timecapsule.timecapsule.dto.UserCapsulesDto.UserCapsuleDto;
import com.Timecapsule.timecapsule.models.UserCapsule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserCapsuleRepository extends JpaRepository<UserCapsule, UUID> {

    List<UserCapsule> findByUserId(UUID userId);

    List<UserCapsule> findByCapsuleId(UUID capsuleId);

    Optional<UserCapsule> findByCapsuleIdAndUserId(UUID capsuleId, UUID userId);
}
