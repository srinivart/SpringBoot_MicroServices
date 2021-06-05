package com.srinivart.moviecatalogservice.resource;


import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.converters.Auto;
import com.srinivart.moviecatalogservice.models.CatalogItems;
import com.srinivart.moviecatalogservice.models.Movie;
import com.srinivart.moviecatalogservice.models.Rating;
import com.srinivart.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItems> getCatalog(@PathVariable("userId") String userId){

        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId, UserRating.class);

            return ratings.getUserRating().stream().map(rating -> {

                // for each movie Id, call the movie info service and get details
            Movie movie = restTemplate.getForObject("http://movie-info-service /movies/"+rating.getMovieId(), Movie.class);

              // put all them together
            return new CatalogItems(movie.getName(), "Test", rating.getRating());
        }).collect(Collectors.toList());

    }
}












/*
   // make the above call using webclient builder
           Movie movie =  webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/"+rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();


            return new CatalogItems(movie.getName(), "Test", rating.getRating());
        }).collect(Collectors.toList());
 */





/*
//        return ratings.stream().map(rating -> {
//          Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);

            // make the above call using webclient builder
//           Movie movie =  webClientBuilder.build()
//                    .get()
//                    .uri("http://localhost:8082/movies/"+rating.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Movie.class)
//                    .block();

 */




/*

        // for each movie ID, call movie info service and get details

        // put them all together

//       return Collections.singletonList(
//         new CatalogItems("Transformers","Test",4)
//            );
 */



/*

        // WebClient.Builder builder = W ebClient.builder();
        //RestTemplate restTemplate = new RestTemplate();  // instead of instantiating everytime, create Bean & Auto wire it

        // get all rated moive ID's
//        List<Rating> ratings = Arrays.asList(
//                new Rating("abcd",4),
//                new Rating("xyz",3)
//        );

        // instead of hardcoding make a call to rest template
        // 2nd argument is you create an instance of class, called parameterized type
        //and pass in the type you want

 */





/*
@RequestMapping("/{userId}")
    public List<CatalogItems> getCatalog(@PathVariable("userId") String userId){

        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/"+userId, UserRating.class);

            return ratings.getUserRating().stream().map(rating -> {

                // for each movie Id, call the movie info service and get details
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);

              // put all them together
            return new CatalogItems(movie.getName(), "Test", rating.getRating());
        }).collect(Collectors.toList());

    }
 */