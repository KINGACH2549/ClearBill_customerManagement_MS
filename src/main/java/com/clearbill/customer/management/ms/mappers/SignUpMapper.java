package com.clearbill.customer.management.ms.mappers;

import com.clearbill.customer.management.ms.dto.customer.CustomerResposeDTOInternalServices;
import com.clearbill.customer.management.ms.dto.onboarding.signupDTO.SignUpRequestDTO;
import com.clearbill.customer.management.ms.dto.onboarding.signupDTO.SignUpResponseDTO;
import com.clearbill.customer.management.ms.entity.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SignUpMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Customer signUpDTOtoCustomerEntity(SignUpRequestDTO signUpRequestDTO){

        return modelMapper.map(signUpRequestDTO,Customer.class);
    }

    public SignUpResponseDTO CustomerEntityToSignUpDTO(Customer customer){

        return modelMapper.map(customer, SignUpResponseDTO.class);
    }

    public CustomerResposeDTOInternalServices CustomerEntityToCustomerDTO(Customer customer){


        return modelMapper.map(customer, CustomerResposeDTOInternalServices.class);
    }

    public List<CustomerResposeDTOInternalServices> ListOfCustomerEntityToCustomerDTO(List<Customer> customers){


        return modelMapper.map(customers, new TypeToken<List<CustomerResposeDTOInternalServices>>() {}.getType());
    }




}
