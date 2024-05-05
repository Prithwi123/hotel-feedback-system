package com.cg.user.dto;

import com.cg.user.entities.Ratings;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserGetDTO {

    String userId;

    private String name;

    private String email;

    private String about;

    private List<Ratings> ratings = new ArrayList<>();

}
