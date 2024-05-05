package com.cg.user.entities;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Builder
public class User {

    @Id
    @Column(name="user_id")
    String userId;

    @Column(name="user_name", length=200)
    private String name;

    @Column(name="user_email")
    private String email;

    @Column(name="user_about", length=1000)
    private String about;

    @Transient
    private List<Ratings> ratings = new ArrayList<>();

}
