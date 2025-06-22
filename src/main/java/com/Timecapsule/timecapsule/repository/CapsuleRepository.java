package com.Timecapsule.timecapsule.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Timecapsule.timecapsule.models.Capsule;

public interface CapsuleRepository extends JpaRepository<Capsule, Long> {
  
}
