package com.ntconsult.hotelreservation.infrastructure.api;

import com.ntconsult.hotelreservation.domain.model.Customer;
import com.ntconsult.hotelreservation.domain.repository.CustomerRepository;
import com.ntconsult.hotelreservation.domain.service.CustomerService;
import com.ntconsult.hotelreservation.infrastructure.dto.input.CustomerInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.CustomerOutputDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerResource extends GenericResource<Customer, Long, CustomerInputDTO, CustomerOutputDTO,
        CustomerRepository> {

    protected CustomerResource(CustomerService service) {
        super(service);
    }

}
