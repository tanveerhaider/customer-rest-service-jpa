package com.simplejava.customer.service;

import com.simplejava.customer.entity.Customer;

import java.util.List;
import java.util.Map;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 2/25/2023
 * Time: 12:05 AM
 */
public interface CustomerService {

    Customer addCustomer(Customer customer);

    List<Customer> findAll();

    Customer findById(Long id);

    Customer findByEmailId(String emailId);

    List<Customer> findByFirstName(String firstName);

    Customer updateCustomer(Long id, Customer customer);

    Customer updateCustomerByFields(Long id, Map<String, Object> fields);

    void deleteCustomer(Long id);


}
