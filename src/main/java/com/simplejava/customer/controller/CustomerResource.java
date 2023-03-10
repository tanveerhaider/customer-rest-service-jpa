package com.simplejava.customer.controller;

import com.simplejava.customer.dto.CustomerDto;
import com.simplejava.customer.entity.Customer;
import com.simplejava.customer.mapper.CustomerMapper;
import com.simplejava.customer.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description : CustomerResource contains CRUD operations related to customer service
 * User: Tanveer Haider
 * Date: 2/25/2023
 * Time: 12:11 AM
 */

@Validated
@Log4j2
@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerResource {
    private final CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto customer) {
        log.info("Inside CustomerDto:" + customer);
        Customer customerEntity = CustomerMapper.MAPPER.mapToEntity(customer);
        Customer customerFromDB = service.addCustomer(customerEntity);
        CustomerDto customerDto = CustomerMapper.MAPPER.mapToDto(customerFromDB);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(customerFromDB.getId()).toUri();
        log.info("Uri:" + location);
        return ResponseEntity.created(location).body(customerDto);

    }

    // id must be positive validation in Path param
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable @Positive Long id) {
        log.info("Inside id :" + id);
        Customer customerById = service.findById(id);
        CustomerDto customerDto = CustomerMapper.MAPPER.mapToDto(customerById);
        return ResponseEntity.ok().body(customerDto);
    }

    // validate for valid emailId
    @GetMapping("/emailId/{emailId}")
    public ResponseEntity<CustomerDto> findByEamilId(@PathVariable @Email String emailId) {
        log.info("Inside get customer by emailId:" + emailId);
        Customer customerByEmailId = service.findByEmailId(emailId);
        CustomerDto customerDto = CustomerMapper.MAPPER.mapToDto(customerByEmailId);
        return ResponseEntity.ok().body(customerDto);
    }

    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<List<CustomerDto>> findAllByFirstName(
            @PathVariable String firstName) {
        log.info("Get all Customers by FirstName:" + firstName);
        List<Customer> customersByFirstName = service.findByFirstName(firstName);
        List<CustomerDto> customers = customersByFirstName.stream()
                .map(t -> CustomerMapper.MAPPER.mapToDto(t))
                .collect(Collectors.toList());
        return ResponseEntity.ok(customers);

    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> findAll() {
        log.info("Inside Get all for customers");
        List<Customer> allCustomers = service.findAll();
        List<CustomerDto> customers = allCustomers.stream()
                .map(t -> CustomerMapper.MAPPER.mapToDto(t))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(customers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id,
                                                      @RequestBody CustomerDto customerDto) {
        log.info("Inside update customer with Id {} and customer {}", id, customerDto);
        Customer customerEntity = CustomerMapper.MAPPER.mapToEntity(customerDto);
        Customer customer = service.updateCustomer(id, customerEntity);
        CustomerDto customerResponse = CustomerMapper.MAPPER.mapToDto(customer);
        return ResponseEntity.ok().body(customerResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomerByFields(@PathVariable Long id,
                                                              @RequestBody Map<String, Object> fields) {
        log.info("In updateCustomerByFields with id:" + id);
        Customer customer = service.updateCustomerByFields(id, fields);
        CustomerDto customerDto = CustomerMapper.MAPPER.mapToDto(customer);
        return ResponseEntity.ok().body(customerDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletetCustomer(@PathVariable Long id) {
        log.info("Delete customer with id:" + id);
        service.deleteCustomer(id);
        return ResponseEntity.ok().body("customer with id " + id + " deleted successfully");
    }

}
