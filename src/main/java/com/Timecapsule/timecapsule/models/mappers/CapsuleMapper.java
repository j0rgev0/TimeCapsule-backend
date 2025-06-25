package com.Timecapsule.timecapsule.models.mappers;


import com.Timecapsule.timecapsule.dto.CapsuleDto;
import com.Timecapsule.timecapsule.dto.CreateCapsuleDto;
import com.Timecapsule.timecapsule.models.Capsule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CapsuleMapper {

    @Mapping(target = "ownerId", source = "owner.id")
    CapsuleDto toCapsuleDto(Capsule capsule);

    Capsule createCapsule(CreateCapsuleDto capsuleDto);

}
