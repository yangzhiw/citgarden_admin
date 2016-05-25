package com.citygarden.repository;

import com.citygarden.domain.UserLevalDefinition;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the UserLevalDefinition entity.
 */
public interface UserLevalDefinitionRepository extends MongoRepository<UserLevalDefinition,String> {

}
