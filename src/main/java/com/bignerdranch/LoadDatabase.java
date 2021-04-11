package com.bignerdranch;

import com.bignerdranch.model.Expertise;
import com.bignerdranch.model.Post;
import com.bignerdranch.model.User;
import com.bignerdranch.respository.ExpertiseRepository;
import com.bignerdranch.respository.PostRepository;
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

    List<Expertise> expertises = Arrays.asList(
            new Expertise("Node"),
            new Expertise("Go"),
            new Expertise("Rails"),
            new Expertise(".NET")
    );
    List<User> users = Arrays.asList(
            new User("Ryan Dahl", expertises.get(0)),
            new User("Rob Pike", expertises.get(1)),
            new User("DHH", expertises.get(2)),
            new User("John Watkins", expertises.get(3))
    );
    List<Post> posts = Arrays.asList(
            new Post("Node is awesome",
                    "Node.js is a JavaScript runtime built on Chrome's V8 JavaScript engine.",
                    users.get(0)),
            new Post("Spring Boot is cooler",
                    "Spring Boot makes it easy to create stand-alone, production-grade Spring based " +
                            "Applications that you can \"just run\".",
                    users.get(0)),
            new Post("Go is faster", "Go is an open source programming language that makes it easy " +
                    "to build simple, reliable, and efficient software.",
                    users.get(1)),
            new Post("'What about me?' -Rails",
                    "Ruby on Rails makes it much easier and more fun. It includes everything you need to " +
                            "build fantastic applications, and you can learn it with the support of our large, " +
                            "friendly community.",
                    users.get(2)),
            new Post(".NET has the gravy",
                    ".NET enables engineers to develop blazing fast web services with ease, utilizing " +
                            "tools developed by Microsoft!",
                    users.get(3))
    );

    @Bean
    public CommandLineRunner initDB(ExpertiseRepository expRepo, UserRepository userRepo, PostRepository postRepo){
        return args -> {
            expertises.forEach(value -> log.info("Preloading " + expRepo.save(value)));
            users.forEach(value -> log.info("Preloading " + userRepo.save(value)));
            posts.forEach(value -> log.info("Preloading " + postRepo.save(value)));
        };
    }
}
