package com.pbp333.springbootrest.springBootRest.repository;

import com.pbp333.springbootrest.springBootRest.Model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path="users", collectionResourceRel="users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    List<User> findByRole(@Param("role") String role);
}
