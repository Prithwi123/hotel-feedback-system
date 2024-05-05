package com.cg.user.mapper;

import com.cg.user.dto.UserGetDTO;
import com.cg.user.dto.UserPostDTO;
import com.cg.user.entities.Ratings;
import com.cg.user.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-02T23:20:58+0530",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class MapStructMapperImpl implements MapStructMapper {

    @Override
    public UserGetDTO userToUserGetDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserGetDTO userGetDTO = new UserGetDTO();

        userGetDTO.setUserId( user.getUserId() );
        userGetDTO.setName( user.getName() );
        userGetDTO.setEmail( user.getEmail() );
        userGetDTO.setAbout( user.getAbout() );
        List<Ratings> list = user.getRatings();
        if ( list != null ) {
            userGetDTO.setRatings( new ArrayList<Ratings>( list ) );
        }

        return userGetDTO;
    }

    @Override
    public User userGetDtoToUser(UserGetDTO userGetDTO) {
        if ( userGetDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( userGetDTO.getUserId() );
        user.name( userGetDTO.getName() );
        user.email( userGetDTO.getEmail() );
        user.about( userGetDTO.getAbout() );
        List<Ratings> list = userGetDTO.getRatings();
        if ( list != null ) {
            user.ratings( new ArrayList<Ratings>( list ) );
        }

        return user.build();
    }

    @Override
    public User userPostDtoToUser(UserPostDTO userPostDTO) {
        if ( userPostDTO == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( userPostDTO.getUserId() );
        user.name( userPostDTO.getName() );
        user.email( userPostDTO.getEmail() );
        user.about( userPostDTO.getAbout() );
        List<Ratings> list = userPostDTO.getRatings();
        if ( list != null ) {
            user.ratings( new ArrayList<Ratings>( list ) );
        }

        return user.build();
    }
}
