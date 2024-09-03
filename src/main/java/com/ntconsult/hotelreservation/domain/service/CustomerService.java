package com.ntconsult.hotelreservation.domain.service;

import com.ntconsult.hotelreservation.domain.model.Customer;
import com.ntconsult.hotelreservation.domain.repository.CustomerRepository;
import com.ntconsult.hotelreservation.infrastructure.dto.input.CustomerInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.CustomerOutputDTO;
import org.springframework.stereotype.Service;

@Service
public class CustomerService extends GenericService<Customer, Long, CustomerInputDTO, CustomerOutputDTO,
        CustomerRepository> {


    protected CustomerService(CustomerRepository genericRepository) {
        super(genericRepository);
    }

}