package com.Timecapsule.timecapsule.services;


import com.Timecapsule.timecapsule.dto.ImagesDto.ImagesDto;
import com.Timecapsule.timecapsule.models.Image;
import com.Timecapsule.timecapsule.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;


    public List<ImagesDto> getAll() {
        List<Image> images = imageRepository.findAll();
        return null;
    }
}
