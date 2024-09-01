package com.ntconsult.hotelreservation.infrastructure.api.controller;

import com.ntconsult.hotelreservation.domain.service.CustomerService;
import com.ntconsult.hotelreservation.infrastructure.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> searchCustomers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        return ResponseEntity.ok(customerService.searchCustomers(name, email)
                .stream()
                .map(customerMapper::toDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerMapper.toDTO(customerService.getCustomerById(id)));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerMapper.toDTO(customerService.createCustomer(customerMapper.toEntity(customerDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerMapper.toDTO(customerService.updateCustomer(id, customerMapper.toEntity(customerDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
