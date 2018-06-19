package com.templateproject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.templateproject.domain.Container;

@Repository
public interface ContainerRepository extends MongoRepository<Container, Long>{
	
}
