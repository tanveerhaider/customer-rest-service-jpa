package com.simplejava.customer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 2/25/2023
 * Time: 12:04 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private long id;

    @JsonProperty("emailAddress")
    @Email(message = "invalid email address")
    private String emailId;
    private String firstName;
    private String lastName;
    private String gender;
    @Pattern(regexp = "^\\d{10}$", message = "invalid cellPhone number entered ")
    private String cellPhone;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String city;

}
