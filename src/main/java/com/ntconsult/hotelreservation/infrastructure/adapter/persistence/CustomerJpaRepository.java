package com.ntconsult.hotelreservation.infrastructure.adapter.persistence;

import com.ntconsult.hotelreservation.domain.model.Customer;

import java.util.List;

public interface CustomerJpaRepository extends GenericJpaRepository<Customer, Long> {
    List<Customer> findCustomersByCustomCriteria(String name, String email);
}
