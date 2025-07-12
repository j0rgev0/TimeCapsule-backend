package com.Timecapsule.timecapsule.services;


import com.Timecapsule.timecapsule.dto.ImagesDto.CreateImageDto;
import com.Timecapsule.timecapsule.dto.ImagesDto.ImagesDto;
import com.Timecapsule.timecapsule.exceptions.AppException;
import com.Timecapsule.timecapsule.models.Capsule;
import com.Timecapsule.timecapsule.models.Image;
import com.Timecapsule.timecapsule.models.mappers.ImageMapper;
import com.Timecapsule.timecapsule.repository.CapsuleRepository;
import com.Timecapsule.timecapsule.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final CapsuleRepository capsuleRepository;
    private final ImageMapper imageMapper;


    public List<ImagesDto> getAll() {
        List<Image> images = imageRepository.findAll();
        return images.stream()
                .map(imageMapper::imagesToDto)
                .collect(Collectors.toList());
    }

    public ImagesDto getImage(UUID id){
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Image not found"));
        return imageMapper.imagesToDto(image);
    }

    public ImagesDto createImage(CreateImageDto img, UUID capsuleId){

        Capsule capsule = capsuleRepository.findById(capsuleId)
                .orElseThrow(() -> new AppException("Capsule not found", HttpStatus.NOT_FOUND));

        Image image = imageMapper.createImage(img);

        image.setCapsule(capsule);

        Image savedImage = imageRepository.save(image);

        return imageMapper.imagesToDto(savedImage);
    }

    public void deleteImage(UUID id){
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Image not found"));

        imageRepository.delete(image);
    }
}
