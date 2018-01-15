package com.pbp333.springbootrest.springBootRest.repository;

import com.pbp333.springbootrest.springBootRest.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class UserCommandLineRunner implements CommandLineRunner{

    private static final Logger log = LoggerFactory.getLogger(UserCommandLineRunner.class);

    @Autowired
    private UserRepository repository;

    @Override
    public void run(String... strings) throws Exception {
        repository.save(new User("Pedro", "Admin"));
        repository.save(new User("Pedro1", "User"));
        repository.save(new User("Pedro2", "User"));

        for(User u : repository.findAll()){
            log.info(u.toString());
        }

        for(User u : repository.findByRole("User")){
            log.info(u.toString());
        }
    }
}
