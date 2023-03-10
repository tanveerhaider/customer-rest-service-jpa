package com.simplejava.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplejava.customer.dto.CustomerDto;
import com.simplejava.customer.entity.Customer;
import com.simplejava.customer.service.CustomerService;
import com.simplejava.customer.util.MockCustomerBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerResource.class)
class CustomerResourceTest {

    private static final String ENDPOINT_URL = "/api/v1/customers";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService mockService;

    @Test
    void testAddCustomer() throws Exception {
        // Setup
        // Configure CustomerService.addCustomer(...).
        final CustomerDto customerDto = MockCustomerBuilder.getFirstCustomerDto();
        final Customer customerEntity = MockCustomerBuilder.getFirstCustomerEntity();

        when(mockService.addCustomer(MockCustomerBuilder.getFirstCustomerEntity())).thenReturn(customerEntity);
        String customerDtoJsonString = MockCustomerBuilder.getCustomerDtoAsJson(customerDto);
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post(ENDPOINT_URL)
                        .content(customerDtoJsonString).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.CREATED.value());
        assertEquals(response.getContentAsString(), customerDtoJsonString);
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());

    }


    @Test
    void testFindById() throws Exception {
        // Setup
        // Configure CustomerService.findById(...).
        final Customer customerEntity = MockCustomerBuilder.getFirstCustomerEntity();
        final CustomerDto customerDto = MockCustomerBuilder.getFirstCustomerDto();
        String customerDtoJsonString = MockCustomerBuilder.getCustomerDtoAsJson(customerDto);

        when(mockService.findById(1L)).thenReturn(customerEntity);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get(ENDPOINT_URL + "/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), customerDtoJsonString);
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
    }

    @Test
    void testFindByEamilId() throws Exception {
        // Setup
        final Customer customerEntity = MockCustomerBuilder.getFirstCustomerEntity();
        final CustomerDto customerDto = MockCustomerBuilder.getFirstCustomerDto();
        String customerDtoJsonString = MockCustomerBuilder.getCustomerDtoAsJson(customerDto);
        final String emailIdParam = "tanveer@gmail.com";
        when(mockService.findByEmailId(emailIdParam)).thenReturn(customerEntity);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v1/customers/emailId/{emailId}", emailIdParam)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), customerDtoJsonString);
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());

    }

    @Test
    void testFindAllByFirstName() throws Exception {
        // Setup
        // Configure CustomerService.findByFirstName(...).
        final List<Customer> customerList = MockCustomerBuilder.getListEntities();
        final List<CustomerDto> customerDtoList = MockCustomerBuilder.getListDTO();
        final String expectedResponse = MockCustomerBuilder.getDtoListAsString(customerDtoList);
        when(mockService.findByFirstName("firstName")).thenReturn(customerList);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        get("/api/v1/customers/firstName/{firstName}", "firstName")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), expectedResponse);
    }

    @Test
    void testFindAllByFirstName_CustomerServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockService.findByFirstName("firstName")).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(
                        get("/api/v1/customers/firstName/{firstName}", "firstName")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), "[]");
    }

    @Test
    void testFindAll() throws Exception {
        // Setup
        // Configure CustomerService.findAll(...).
        final List<Customer> customerList = MockCustomerBuilder.getListEntities();
        final List<CustomerDto> customerDtoList = MockCustomerBuilder.getListDTO();
        final String expectedResponse = MockCustomerBuilder.getDtoListAsString(customerDtoList);
        when(mockService.findAll()).thenReturn(customerList);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v1/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), expectedResponse);
    }

    @Test
    void testFindAll_CustomerServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockService.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v1/customers")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), "[]");
    }

    @Test
    void testUpdateCustomer() throws Exception {
        // Setup
        // Configure CustomerService.updateCustomer(...).
        final Customer customerEntity = MockCustomerBuilder.getFirstCustomerEntity();
        final CustomerDto customerDto = MockCustomerBuilder.getFirstCustomerDto();
        String customerDtoJsonString = MockCustomerBuilder.getCustomerDtoAsJson(customerDto);
        when(mockService.updateCustomer(1l, customerEntity)).thenReturn(customerEntity);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/api/v1/customers/{id}", 1l)
                        .content(customerDtoJsonString).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), customerDtoJsonString);
    }

    @Test
    void testUpdateCustomerByFields() throws Exception {
        // Setup
        // Configure CustomerService.updateCustomerByFields(...).
        final Customer customerEntity = MockCustomerBuilder.getFirstCustomerEntity();
        final Map<String, Object> fields = new HashMap<>();
        fields.put("emailAddress", "tanveer@rediffmail.com");
        fields.put("lastName", "haider");
        final CustomerDto customerDto = MockCustomerBuilder.getFirstCustomerDto();
        String customerDtoJsonString = MockCustomerBuilder.getCustomerDtoAsJson(customerDto);
        final String mapAsString = new ObjectMapper().writeValueAsString(fields);
        when(mockService.updateCustomerByFields(1L, fields)).thenReturn(customerEntity);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(patch("/api/v1/customers/{id}", 1L)
                        .content(mapAsString).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), customerDtoJsonString);
    }

    @Test
    void testDeletetCustomer() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/api/v1/customers/{id}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(response.getStatus(), HttpStatus.OK.value());
        assertEquals(response.getContentAsString(), "customer with id 0 deleted successfully");
        verify(mockService).deleteCustomer(0L);
    }
}
