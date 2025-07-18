package com.Timecapsule.timecapsule.models.mappers;


import com.Timecapsule.timecapsule.dto.UserCapsulesDto.UserCapsuleDto;
import com.Timecapsule.timecapsule.models.UserCapsule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserCapsuleMapper {

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "capsuleId", source = "capsule.id")
    UserCapsuleDto userCapsuleToDto(UserCapsule userCapsule);
}
