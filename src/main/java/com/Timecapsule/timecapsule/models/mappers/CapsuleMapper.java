package com.Timecapsule.timecapsule.models.mappers;


import com.Timecapsule.timecapsule.dto.CapsulesDtos.CapsuleDto;
import com.Timecapsule.timecapsule.dto.CapsulesDtos.CreateCapsuleDto;
import com.Timecapsule.timecapsule.dto.CapsulesDtos.UpdateCapsuleDto;
import com.Timecapsule.timecapsule.models.Capsule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CapsuleMapper {

    @Mapping(target = "ownerId", source = "owner.id")
    CapsuleDto toCapsuleDto(Capsule capsule);

    Capsule createCapsule(CreateCapsuleDto capsuleDto);

    void updateCapsuleFromDto(UpdateCapsuleDto dto, @MappingTarget Capsule entity);

}
