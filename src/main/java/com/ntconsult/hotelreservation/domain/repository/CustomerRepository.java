package com.ntconsult.hotelreservation.domain.repository;

import com.ntconsult.hotelreservation.domain.model.Customer;
import com.ntconsult.hotelreservation.infrastructure.adapter.persistence.CustomerJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends GenericRepository<Customer, Long>, CustomerJpaRepository {
}