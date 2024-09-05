package com.clearbill.customer.management.ms.mappers;

import com.clearbill.customer.management.ms.dto.customer.UpdateCustomerRequestDTO;
import com.clearbill.customer.management.ms.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateCustomerEntityMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Customer updateCustomerRequestDTOtoCustomerEntity(UpdateCustomerRequestDTO updateCustomerRequestDTO){

        return modelMapper.map(updateCustomerRequestDTO,Customer.class);
    }


}
