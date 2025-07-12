package com.Timecapsule.timecapsule.controller;


import com.Timecapsule.timecapsule.config.CloudinaryConfig;
import com.Timecapsule.timecapsule.dto.ErrorDto;
import com.Timecapsule.timecapsule.dto.ImagesDto.CreateImageDto;
import com.Timecapsule.timecapsule.dto.ImagesDto.ImagesDto;
import com.Timecapsule.timecapsule.services.ImageService;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/images")
public class ImagesController {

    private final ImageService imageService;

    CloudinaryConfig cloudinaryConfig = new CloudinaryConfig();

    @GetMapping
    public ResponseEntity<List<ImagesDto>> getAllImages(){
      List<ImagesDto> images = imageService.getAll();
      return ResponseEntity.ok(images);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImage(@PathVariable UUID id){
        try {
            ImagesDto image = imageService.getImage(id);
            return ResponseEntity.ok(image);
        }catch (Exception e){
            return ResponseEntity.status(404).body(new ErrorDto("Image not Found"));
        }
    }

    @PostMapping
    public ResponseEntity<?> createImage(@RequestParam("file") MultipartFile image, @RequestParam("capsuleId") UUID id){
        try {
            Cloudinary cloudinary = cloudinaryConfig.cloudinary();
            File file = convertToFile(image);

            Map result = cloudinary.uploader().upload(file, null);

            String url = result.get("url").toString();
            String publicId = result.get("public_id").toString();

            CreateImageDto createImageDto = new CreateImageDto(url, publicId);

            ImagesDto imageDto = imageService.createImage(createImageDto, id);

            return ResponseEntity.created(URI.create("/api/images/" + imageDto.getId())).body(imageDto);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDto("Internal server error"));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable UUID id){
        try {
            imageService.deleteImage(id);
            return ResponseEntity.ok("Image deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(404).body(new ErrorDto("Image not Found"));
        }
    }


    private File convertToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(multipartFile.getBytes());
        }
        return convFile;
    }

}
