package com.myntra.api.inventory.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.myntra.api.inventory.entity.mongo.ProductGeneral;

@Repository
public interface ProductGeneralRepository extends MongoRepository<ProductGeneral, String> {
	
}
