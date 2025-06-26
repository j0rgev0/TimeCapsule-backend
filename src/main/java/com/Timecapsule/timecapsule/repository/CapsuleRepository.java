package com.Timecapsule.timecapsule.repository;

import java.util.Optional;
import java.util.UUID;

import com.Timecapsule.timecapsule.dto.CapsulesDtos.CapsuleDto;
import com.Timecapsule.timecapsule.dto.CapsulesDtos.CreateCapsuleDto;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Timecapsule.timecapsule.models.Capsule;

public interface CapsuleRepository extends JpaRepository<Capsule, UUID> {

    Optional<Capsule> findByTitle(String title);
}
