package com.example.k8slogtest.repository;

import com.example.k8slogtest.vo.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}