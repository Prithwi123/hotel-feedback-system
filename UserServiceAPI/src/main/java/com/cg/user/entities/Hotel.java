package com.cg.user.entities;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "hotel")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Hotel {
    private String hotel_id;
    private String name;
    private String location;
    private String about;
}
