package com.ntconsult.hotelreservation.infrastructure.adapter.persistence;

import com.ntconsult.hotelreservation.domain.model.Customer;
import com.ntconsult.hotelreservation.domain.model.QCustomer;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerJpaRepositoryImpl implements CustomerJpaRepository {

    private final EntityManager entityManager;

    public CustomerJpaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Customer> findCustomersByCustomCriteria(String name, String email) {
        QCustomer customer = QCustomer.customer;
        BooleanExpression predicate = customer.isNotNull();

        if (name != null && !name.isEmpty()) {
            predicate = predicate.and(customer.name.containsIgnoreCase(name));
        }

        if (email != null && !email.isEmpty()) {
            predicate = predicate.and(customer.email.eq(email));
        }

        return new JPAQueryFactory(this.entityManager).selectFrom(customer)
                .where(predicate)
                .fetch();
    }
}