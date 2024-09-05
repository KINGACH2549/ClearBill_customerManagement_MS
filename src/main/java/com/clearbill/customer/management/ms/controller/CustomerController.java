package com.clearbill.customer.management.ms.controller;

import com.clearbill.customer.management.ms.dto.customer.CustomerResposeDTOInternalServices;
import com.clearbill.customer.management.ms.dto.customer.UpdateCustomerRequestDTO;
import com.clearbill.customer.management.ms.dto.onboarding.signupDTO.SignUpResponseDTO;
import com.clearbill.customer.management.ms.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1/customerManagement/customerDetails")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);


    @Autowired
    private CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<SignUpResponseDTO> getCustomerDetails(@PathVariable String customerId) throws IllegalAccessException {

        LOGGER.info("Request Recieved for Get Customer Details");
        LOGGER.trace("Customer Id :"+customerId);

        SignUpResponseDTO response= customerService.getCustomerById(customerId);

        LOGGER.info("Response from Customer Details Service");
        LOGGER.trace(response.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/query")
    public ResponseEntity<?> getCustomerDetailsByQueryParams(@RequestHeader String Authorization , @RequestParam HashMap<Object, Object> params) throws IllegalAccessException {

         LOGGER.info("Querying customer details based on unique parameters");
         LOGGER.trace("Querying Customer details for params :"+params.toString());

         SignUpResponseDTO response = customerService.queryCustomerByUniqueParams(params,Authorization);

         LOGGER.info("Response from Customer Details Service");
         LOGGER.trace(response.toString());

         return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/queryCustomers")
    public ResponseEntity<List<CustomerResposeDTOInternalServices>> getCustomerDetailsByQueryParamsForInternalServices(@RequestParam(value = "customerIds",required = false) List<String> params, @RequestParam(value = "limit", required = false) Integer limit, @RequestParam(value = "offset",required = false) Integer offset , @RequestParam(value = "requestedCustomerId",required = false) String requestedCustomerId, @RequestParam(value = "targetCustomerEmail",required = false) String targetCustomerEmail) throws IllegalAccessException {

        LOGGER.info("Querying customer details based on unique parameters");
//        LOGGER.trace("Querying Customer details for params :"+params.toString());

        HashMap<String,Object> queryParams = new HashMap<>();
        queryParams.put("limit",50);
        queryParams.put("offset",0);

        if(limit!=null)queryParams.put("limit",limit);
        if (offset!=null) queryParams.put("offset",offset);
        if(requestedCustomerId!=null)queryParams.put("requestedCustomerId",requestedCustomerId);
        if(targetCustomerEmail!=null)queryParams.put("targetCustomerEmail",targetCustomerEmail);

        List<CustomerResposeDTOInternalServices> response = customerService.queryCustomersByUniqueParamsForInternalServices(params,queryParams);

        LOGGER.info("Response from Customer Details Service");
        LOGGER.trace(response.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/queryCustomer")
    public ResponseEntity<CustomerResposeDTOInternalServices> getCustomerDetailByQueryParamsForInternalServices(@RequestParam HashMap<Object, Object> params) throws IllegalAccessException {

        LOGGER.info("Querying customer details based on unique parameters");
        LOGGER.trace("Querying Customer details for params :"+params.toString());

        CustomerResposeDTOInternalServices response = customerService.queryCustomerByUniqueParamsForInternalServices(params);

        LOGGER.info("Response from Customer Details Service");
        LOGGER.trace(response.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{customerId}")
    public ResponseEntity<SignUpResponseDTO> updateCustomerDetails(@RequestBody UpdateCustomerRequestDTO updateCustomerRequestDTO, @PathVariable String customerId) throws IllegalAccessException {

        LOGGER.info("Request Recieved for Updating Customer Details");
        LOGGER.trace("Customer Id :"+customerId);

        SignUpResponseDTO response= customerService.updateCustomerDetails(customerId,updateCustomerRequestDTO);

        LOGGER.info("Response from Customer Details Service");
        LOGGER.trace(response.toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
