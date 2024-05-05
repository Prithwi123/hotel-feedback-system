package com.cg.user.services;

import java.util.*;
import java.util.stream.Collectors;

import com.cg.user.entities.Hotel;
import com.cg.user.entities.Ratings;
import com.cg.user.entities.User;
import com.cg.user.exception.ResourceNotFoundException;
import com.cg.user.external_service.HotelServiceCall;
import com.cg.user.external_service.RatingServiceCall;
import com.cg.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

@Service
@ComponentScan(basePackages = {"com.cg.user.services"})
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JaxbProcessServiceImpl processUtility;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelServiceCall hotelServiceCall;

    @Autowired
    private RatingServiceCall ratingServiceCall;



    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String id =UUID.randomUUID().toString();
        user.setUserId(id);
        logger.info("unique user id has been set");
        return userRepository.save(user);
    }


    @Override
    public List<User> getAllUser() {

        List<User> users = userRepository.findAll();
        for(User user: users){

            //fetch user id of the current user
            String id = user.getUserId();

            //calling rating service api to fetch ratings using feign client
            List<Ratings> obj = ratingServiceCall.getRating(id);
            logger.info("calling rating service using feign client to get rating records");

            //looping through all ratings and fetch the hotel for which the rating is given

            List<Ratings> ratingsList = obj.stream().map(rating -> {

                //call hotel service api to fetch hotels using feign client
                Hotel hotel = hotelServiceCall.getHotel(rating.getHotelId());
                logger.info("calling hotel service using feign client to get hotel records");

                //set hotel details in
                rating.setHotel(hotel);
                return rating;
            }).collect(Collectors.toList());

            //update user record
            user.setRatings(ratingsList);
            logger.info("all user records with ratings and hotel details fetched");

        }

        return users;
    }

    @Override
    public User getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with Given Id is not found in the database"));
        Ratings[] ratingArray= restTemplate.getForObject("http://RATING-SERVICE/ratings/"+user.getUserId(),Ratings[].class);
        logger.info("ratings records fetched using rest template: ",ratingArray);

        List<Ratings> obj = Arrays.stream(ratingArray).toList();

        List<Ratings> ratingsList = obj.stream().map(rating -> {
            Hotel hotel = hotelServiceCall.getHotel(rating.getHotelId());
            logger.info("hotel records fetched using feign client");
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingsList);
        logger.info("specific user record with ratings and hotel details fetched");
        return user;

    }

}


