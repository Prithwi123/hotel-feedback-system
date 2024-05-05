package com.cg.user.mapper;

import com.cg.user.entities.User;
import com.cg.user.dto.UserGetDTO;
import com.cg.user.dto.UserPostDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    public UserGetDTO userToUserGetDto(User user);

    public User userGetDtoToUser(UserGetDTO userGetDTO);

    public User userPostDtoToUser(UserPostDTO userPostDTO);
}
