package com.templateproject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.templateproject.domain.Todo;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {

}
