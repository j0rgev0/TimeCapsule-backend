package com.Timecapsule.timecapsule.models.mappers;


import com.Timecapsule.timecapsule.dto.ImagesDto.ImagesDto;
import com.Timecapsule.timecapsule.models.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(target = "ownerId", source = "owner.id")
    ImagesDto imagesToDto(Image image);
}
