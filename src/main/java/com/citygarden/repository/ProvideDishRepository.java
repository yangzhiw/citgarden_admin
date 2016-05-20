package com.citygarden.repository;

import com.citygarden.domain.ProvideDish;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the ProvideDish entity.
 */
public interface ProvideDishRepository extends MongoRepository<ProvideDish,String> {

    ProvideDish findByProvideIdAndName(String provideMerchantId, String name);
}
