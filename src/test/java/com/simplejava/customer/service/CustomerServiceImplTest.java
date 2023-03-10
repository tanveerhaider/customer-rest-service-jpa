package com.simplejava.customer.service;

import com.simplejava.customer.entity.Customer;
import com.simplejava.customer.exception.CustomerNotFoundException;
import com.simplejava.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository mockCustomerRepository;

    private CustomerServiceImpl customerServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        customerServiceImplUnderTest = new CustomerServiceImpl(mockCustomerRepository);
    }

    private Customer getMockCustomer() {
        return new Customer(1L, "tanveer@gmail.com", "Tanveer", "H", "M", "001-100-1111", "02169",
                "Quincy");
    }

    @Test
    void testAddCustomer() {
        // Setup
        final Customer customer = getMockCustomer();
        final Customer expectedResult = getMockCustomer();

        // Configure CustomerRepository.save(...).
        final Customer customer1 = getMockCustomer();
        when(mockCustomerRepository.save(
                getMockCustomer())).thenReturn(customer1);

        // Run the test
        final Customer result = customerServiceImplUnderTest.addCustomer(customer);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll() {
        // Setup
        final List<Customer> expectedResult = List.of(getMockCustomer());
        // Configure CustomerRepository.findAll(...).
        final List<Customer> customerList = List.of(getMockCustomer());
        when(mockCustomerRepository.findAll()).thenReturn(customerList);

        // Run the test
        final List<Customer> result = customerServiceImplUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_CustomerRepositoryReturnsNoItems() {
        // Setup
        when(mockCustomerRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Customer> result = customerServiceImplUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindById() {
        // Setup
        final Customer expectedResult = getMockCustomer();

        // Configure CustomerRepository.findById(...).
        final Optional<Customer> customer = Optional.of(getMockCustomer());
        when(mockCustomerRepository.findById(0L)).thenReturn(customer);

        // Run the test
        final Customer result = customerServiceImplUnderTest.findById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindById_CustomerRepositoryReturnsAbsent() {
        // Setup
        when(mockCustomerRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> customerServiceImplUnderTest.findById(0L))
                .isInstanceOf(CustomerNotFoundException.class);
    }

    @Test
    void testFindByEmailId() {
        // Setup
        final Customer expectedResult = getMockCustomer();

        // Configure CustomerRepository.findByEmailId(...).
        final Optional<Customer> customer = Optional.of(getMockCustomer());
        when(mockCustomerRepository.findByEmailId("tanveer@gmail.com")).thenReturn(customer);

        // Run the test
        final Customer result = customerServiceImplUnderTest.findByEmailId("tanveer@gmail.com");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        assertThat(expectedResult.getEmailId()).isEqualTo(expectedResult.getEmailId());
    }

    @Test
    void testFindByEmailId_CustomerRepositoryReturnsAbsent() {
        // Setup
        when(mockCustomerRepository.findByEmailId("emailId")).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> customerServiceImplUnderTest.findByEmailId("emailId"))
                .isInstanceOf(CustomerNotFoundException.class);
    }

    @Test
    void testFindByFirstName() {
        // Setup
        final List<Customer> expectedResult = List.of(getMockCustomer());
        // Configure CustomerRepository.findByFirstName(...).
        final Optional<List<Customer>> customers = Optional.of(
                List.of(getMockCustomer()));
        when(mockCustomerRepository.findByFirstName("firstName")).thenReturn(customers);

        // Run the test
        final List<Customer> result = customerServiceImplUnderTest.findByFirstName("firstName");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindByFirstName_CustomerRepositoryReturnsAbsent() {
        // Setup
        when(mockCustomerRepository.findByFirstName("firstName")).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> customerServiceImplUnderTest.findByFirstName("firstName"))
                .isInstanceOf(CustomerNotFoundException.class);
    }

    @Test
    void testFindByFirstName_CustomerRepositoryReturnsNoItems() {
        // Setup
        when(mockCustomerRepository.findByFirstName("firstName")).thenReturn(Optional.of(Collections.emptyList()));

        // Run the test
        final List<Customer> result = customerServiceImplUnderTest.findByFirstName("firstName");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testUpdateCustomer() {
        // Setup
        final Customer customer = new Customer(0L, "emailId", "firstName", "lastName", "gender", "cellPhone", "zipCode",
                "city");
        final Customer expectedResult = new Customer(0L, "emailId", "firstName", "lastName", "gender", "cellPhone",
                "zipCode", "city");

        // Configure CustomerRepository.findById(...).
        final Optional<Customer> customer1 = Optional.of(
                new Customer(0L, "emailId", "firstName", "lastName", "gender", "cellPhone", "zipCode", "city"));
        when(mockCustomerRepository.findById(0L)).thenReturn(customer1);

        // Configure CustomerRepository.save(...).
        final Customer customer2 = new Customer(0L, "emailId", "firstName", "lastName", "gender", "cellPhone",
                "zipCode", "city");
        when(mockCustomerRepository.save(
                new Customer(0L, "emailId", "firstName", "lastName", "gender", "cellPhone", "zipCode",
                        "city"))).thenReturn(customer2);

        // Run the test
        final Customer result = customerServiceImplUnderTest.updateCustomer(0L, customer);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockCustomerRepository).save(
                new Customer(0L, "emailId", "firstName", "lastName", "gender", "cellPhone", "zipCode", "city"));
    }

    @Test
    void testUpdateCustomer_CustomerRepositoryFindByIdReturnsAbsent() {
        // Setup
        final Customer customer = new Customer(0L, "emailId", "firstName", "lastName", "gender", "cellPhone", "zipCode",
                "city");
        when(mockCustomerRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> customerServiceImplUnderTest.updateCustomer(0L, customer))
                .isInstanceOf(CustomerNotFoundException.class);
    }

    @Test
    void testUpdateCustomerByFields() {
        // Setup
        final Map<String, Object> fields = Map.ofEntries(Map.entry("emailId", "tanveer@gmail.com"));
        final Customer expectedResult = getMockCustomer();

        // Configure CustomerRepository.findById(...).
        final Optional<Customer> customer = Optional.of(getMockCustomer());
        when(mockCustomerRepository.findById(1L)).thenReturn(customer);

        // Configure CustomerRepository.save(...).
        final Customer customer1 = getMockCustomer();
        when(mockCustomerRepository.save(getMockCustomer())).thenReturn(customer1);

        // Run the test
        final Customer result = customerServiceImplUnderTest.updateCustomerByFields(1L, fields);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(mockCustomerRepository).save(getMockCustomer());
    }

    @Test
    void testUpdateCustomerByFields_CustomerRepositoryFindByIdReturnsAbsent() {
        // Setup
        final Map<String, Object> fields = Map.ofEntries(Map.entry("value", "value"));
        when(mockCustomerRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> customerServiceImplUnderTest.updateCustomerByFields(0L, fields))
                .isInstanceOf(CustomerNotFoundException.class);
    }

    @Test
    void testDeleteCustomer() {
        // Setup
        // Run the test
        customerServiceImplUnderTest.deleteCustomer(0L);

        // Verify the results
        verify(mockCustomerRepository).deleteById(0L);
    }
}
