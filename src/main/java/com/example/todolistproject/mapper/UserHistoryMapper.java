package com.example.todolistproject.mapper;

import com.example.todolistproject.model.dto.UserDto;
import com.example.todolistproject.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserHistoryMapper {

    UserHistoryMapper INSTANCE = Mappers.getMapper(UserHistoryMapper.class);

    User toUser(UserDto userDto);
    UserDto toUserDto(User user);
    List<User> toUserList(List<UserDto> userDtoList);
    List<UserDto> toUserDtoList(List<User> userList);
}
