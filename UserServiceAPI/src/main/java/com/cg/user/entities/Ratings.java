package com.cg.user.entities;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@XmlRootElement(name = "ratings")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Ratings {
    private String ratingId;
    private String userId;

    private String hotelId;

    private String rating;

    private String review;

    private Hotel hotel;

}
