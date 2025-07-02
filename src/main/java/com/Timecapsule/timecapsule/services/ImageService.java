package com.Timecapsule.timecapsule.services;


import com.Timecapsule.timecapsule.dto.ImagesDto.ImagesDto;
import com.Timecapsule.timecapsule.models.Image;
import com.Timecapsule.timecapsule.models.mappers.ImageMapper;
import com.Timecapsule.timecapsule.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;


    public List<ImagesDto> getAll() {
        List<Image> images = imageRepository.findAll();
        return images.stream()
                .map(imageMapper::imagesToDto)
                .collect(Collectors.toList());
    }
}
