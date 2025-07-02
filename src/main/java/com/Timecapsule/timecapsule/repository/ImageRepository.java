package com.Timecapsule.timecapsule.repository;

import com.Timecapsule.timecapsule.models.Capsule;
import com.Timecapsule.timecapsule.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ImageRepository  extends JpaRepository<Image, UUID> {

    Optional<Image> findByPublicId(String publicId);
}

