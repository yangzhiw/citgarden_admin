package com.citygarden.repository;

import com.citygarden.domain.Order;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Spring Data MongoDB repository for the Order entity.
 */
public interface OrderRepository extends MongoRepository<Order,String> {

    List<Order> findByOrderStatus(String status);
}
