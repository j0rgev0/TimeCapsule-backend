package com.Timecapsule.timecapsule.models.mappers;

import com.Timecapsule.timecapsule.dto.SignUpDto;
import com.Timecapsule.timecapsule.dto.UserDto;
import com.Timecapsule.timecapsule.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Bean;


@Mapper(componentModel = "spring")
public interface UserMapper {


   UserDto toUserDto(User user);

   @Mapping(target = "password", ignore = true)
   User signUpToUser(SignUpDto userDto);

}
