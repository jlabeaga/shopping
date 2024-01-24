package com.example.shopping.mapper;

import com.example.shopping.dto.UserDTO;
import com.example.shopping.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserEntity toUserEntity(UserDTO user);
    UserDTO toUserDTO(UserEntity user);

}
