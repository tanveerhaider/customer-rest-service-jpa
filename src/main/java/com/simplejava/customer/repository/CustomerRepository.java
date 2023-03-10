package com.simplejava.customer.repository;

import com.simplejava.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 2/25/2023
 * Time: 12:35 AM
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmailId(String email);

    Optional<List<Customer>> findByFirstName(String firstName);


}
