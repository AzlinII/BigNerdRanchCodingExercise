package com.bignerdranch;

import com.bignerdranch.model.Expertise;
import com.bignerdranch.model.User;
import com.bignerdranch.respository.ExpertiseRepository;
import com.bignerdranch.respository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    public CommandLineRunner initDB(ExpertiseRepository expRepo, UserRepository userRepo){
        List<Expertise> expertises = Arrays.asList(
                new Expertise(1L, "Node"),
                new Expertise(2L, "Go"),
                new Expertise(3L, "Rails"),
                new Expertise(4L, ".NET")
        );
        List<User> users = Arrays.asList(
                new User(1L, "Ryan Dahl", expertises.get(0)),
                new User(2L, "Rob Pike", expertises.get(1)),
                new User(3L, "DHH", expertises.get(2)),
                new User(4L, "John Watkins", expertises.get(3))
        );

        return args -> {
            expertises.forEach(value -> log.info("Preloading " + expRepo.save(value)));
            users.forEach(value -> log.info("Preloading " + userRepo.save(value)));
        };

    }
}
