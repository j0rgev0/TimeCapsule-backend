package com.Timecapsule.timecapsule.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Timecapsule.timecapsule.models.Capsule;

public interface CapsuleRepository extends JpaRepository<Capsule, UUID> {
    Optional<Capsule> findByTitle(String title);

    List<Capsule> findByOwnerId(UUID ownerId);
}
