package com.clearbill.customer.management.ms.service;

import com.clearbill.customer.management.ms.dto.customer.CustomerResposeDTOInternalServices;
import com.clearbill.customer.management.ms.dto.customer.UpdateCustomerRequestDTO;
import com.clearbill.customer.management.ms.dto.onboarding.signupDTO.SignUpResponseDTO;

import java.util.HashMap;
import java.util.List;

public interface CustomerService {

    public SignUpResponseDTO getCustomerById(String customerId);

    public SignUpResponseDTO updateCustomerDetails(String customerId, UpdateCustomerRequestDTO updateCustomerRequestDTO) throws IllegalAccessException;

    public SignUpResponseDTO queryCustomerByUniqueParams(HashMap<Object,Object> params,String authToken);

    public CustomerResposeDTOInternalServices queryCustomerByUniqueParamsForInternalServices(HashMap<Object, Object> params);

    public List<CustomerResposeDTOInternalServices> queryCustomersByUniqueParamsForInternalServices(Iterable<String> params,HashMap<String,Object> queryParams);
}
