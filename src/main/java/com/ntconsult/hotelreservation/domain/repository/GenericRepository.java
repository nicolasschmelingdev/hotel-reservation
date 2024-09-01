package com.ntconsult.hotelreservation.domain.repository;

import com.ntconsult.hotelreservation.domain.model.GenericEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T extends GenericEntity<T>, ID> extends JpaRepository<T, ID> {

    Page<T> findAll(Specification<T> spec, Pageable paging);

}
