package com.Timecapsule.timecapsule.controller;


import com.Timecapsule.timecapsule.dto.ImagesDto.ImagesDto;
import com.Timecapsule.timecapsule.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/images")
public class ImagesController {


    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<List<ImagesDto>> getAllImages(){
      List<ImagesDto> images = imageService.getAll();

      return ResponseEntity.ok(images);
    }
}
