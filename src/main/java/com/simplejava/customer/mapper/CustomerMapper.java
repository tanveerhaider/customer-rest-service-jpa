package com.simplejava.customer.mapper;

import com.simplejava.customer.dto.CustomerDto;
import com.simplejava.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 2/25/2023
 * Time: 12:13 AM
 */
@Mapper
public interface CustomerMapper {

    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    CustomerDto mapToDto(Customer customer);

    Customer mapToEntity(CustomerDto customerDto);
}
