package com.Timecapsule.timecapsule.repository;

import com.Timecapsule.timecapsule.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository  extends JpaRepository<Image, UUID> {
}

