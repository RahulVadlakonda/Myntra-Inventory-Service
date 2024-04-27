package com.myntra.api.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myntra.api.inventory.entity.mongo.ErrorCodes;

public interface ErrorCodesRepository extends JpaRepository<ErrorCodes, Long> {

}
