package com.srinivart.ratingsdataservice.resource;

import com.srinivart.ratingsdataservice.models.Rating;
import com.srinivart.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId,4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
         List<Rating> ratings = Arrays.asList(
                 new Rating("telugu", 3),
                 new Rating("hindi",2),
                 new Rating ("english",5)

         );

         UserRating userRating = new UserRating();
         userRating.setUserRating(ratings);
         return userRating;
    }


}








/*
  @RequestMapping("/users/{userId}")
    public List<Rating> getUserRating(@PathVariable("userId") String userId){
         List<Rating> ratings = Arrays.asList(
                 new Rating("telugu", 3),
                 new Rating("hindi",2),
                 new Rating ("english",5)

         );
 */