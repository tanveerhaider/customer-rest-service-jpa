package com.simplejava.customer.service;

import com.simplejava.customer.entity.Customer;
import com.simplejava.customer.exception.CustomerNotFoundException;
import com.simplejava.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 2/25/2023
 * Time: 12:10 AM
 */

@Log4j2
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        Customer customerFromDB = customerRepository.save(customer);
        log.info("customerFromDB:" + customerFromDB.getId());
        return customerFromDB;
    }


    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(" Customer with id:" + id + " doesnot exist"));
    }

    @Override
    public Customer findByEmailId(String emailId) {
        return customerRepository.findByEmailId(emailId)
                .orElseThrow(() -> new CustomerNotFoundException(" Customer with emailId:" + emailId + " doesnot exist"));
    }


    @Override
    public List<Customer> findByFirstName(String firstName) {
        Optional<List<Customer>> customersByFirstName = Optional.ofNullable(customerRepository
                .findByFirstName(firstName)
                .orElseThrow(() -> new CustomerNotFoundException(" Customer with firstName:" + firstName + " doesnot exist")));
        return customersByFirstName.get();
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer existingCustomer = customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(" Customer with id:" + id + " doesnot exist"));
        existingCustomer.setEmailId(customer.getEmailId());
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setGender(customer.getGender());
        existingCustomer.setCellPhone(customer.getCellPhone());
        existingCustomer.setCity(customer.getCity());
        existingCustomer.setZipCode(customer.getZipCode());
        customerRepository.save(existingCustomer);
        return existingCustomer;

    }

    @Override
    public Customer updateCustomerByFields(Long id, Map<String, Object> fields) {
        Customer existingCustomer = customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(" Customer with id:" + id + " doesnot exist"));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Customer.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingCustomer, value);
        });
        customerRepository.save(existingCustomer);
        return existingCustomer;
    }


    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
