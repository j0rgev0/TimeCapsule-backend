package com.Timecapsule.timecapsule.repository;

import com.Timecapsule.timecapsule.models.UserCapsule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserCapsuleRepository extends JpaRepository<UserCapsule, UUID> {

    List<UserCapsule> findByUserId(UUID userId);
    List<UserCapsule> findByCapsuleId(UUID capsuleId);
}
