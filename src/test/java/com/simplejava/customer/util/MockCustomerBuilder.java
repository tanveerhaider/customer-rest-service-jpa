package com.simplejava.customer.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplejava.customer.dto.CustomerDto;
import com.simplejava.customer.entity.Customer;

import java.util.Arrays;
import java.util.List;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 3/3/2023
 * Time: 1:13 PM
 */

public class MockCustomerBuilder {

    public static Customer getFirstCustomerEntity() {
        return new Customer(1l, "tanveer@gmail.com", "Tanveer",
                "Haider", "M", "6475151000", "02169", "Weymouuth");
    }

    public static String getCustomerEntityAsJson(Customer customer) throws JacksonException {
        String jsonString = new ObjectMapper().writeValueAsString(customer);
        return jsonString;
    }

    public static CustomerDto getFirstCustomerDto() {
        return new CustomerDto(1l, "tanveer@gmail.com", "Tanveer",
                "Haider", "M", "6475151000", "02169", "Weymouuth");
    }

    public static String getCustomerDtoAsJson(CustomerDto customerDto) throws JacksonException {
        String jsonString = new ObjectMapper().writeValueAsString(customerDto);
        return jsonString;
    }

    public static CustomerDto getSecondCustomerDto() {
        return new CustomerDto(2l, "Osman@gmail.com", "Osman", "Haider",
                "M", "6475151000", "02169", "Weymouuth");
    }


    public static Customer getSecondCustomerEntity() {
        return new Customer(2l, "Osman@gmail.com", "Osman", "Haider",
                "M", "6475151000", "02169", "Weymouuth");
    }

    public static List<CustomerDto> getListDTO() {
        return Arrays.asList(getFirstCustomerDto(), getSecondCustomerDto());

    }

    public static String getDtoListAsString(List<CustomerDto> dtoList) throws JacksonException {
        return new ObjectMapper().writeValueAsString(dtoList);

    }

    public static List<Customer> getListEntities() {
        return Arrays.asList(getFirstCustomerEntity(), getSecondCustomerEntity());
    }
}
