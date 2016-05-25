package com.citygarden.repository;

import com.citygarden.domain.ProfitReports;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the ProfitReports entity.
 */
public interface ProfitReportsRepository extends MongoRepository<ProfitReports,String> {

}
