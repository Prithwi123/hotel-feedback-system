package com.cg.user.dto;

import com.cg.user.entities.Ratings;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserPostDTO {
    String userId;

    private String name;

    private String email;

    private String about;

    private List<Ratings> ratings = new ArrayList<>();
}
