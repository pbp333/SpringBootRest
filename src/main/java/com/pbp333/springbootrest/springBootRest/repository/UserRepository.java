package com.pbp333.springbootrest.springBootRest.repository;

import com.pbp333.springbootrest.springBootRest.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{


}
