package com.citygarden.repository;

import com.citygarden.domain.RePertoryManager;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the RePertoryManager entity.
 */
public interface RePertoryManagerRepository extends MongoRepository<RePertoryManager,String> {

}
