package com.clearbill.customer.management.ms.service.implementations;

import com.clearbill.customer.management.ms.dto.customer.CustomerResposeDTOInternalServices;
import com.clearbill.customer.management.ms.dto.customer.UpdateCustomerRequestDTO;
import com.clearbill.customer.management.ms.dto.onboarding.signupDTO.SignUpResponseDTO;
import com.clearbill.customer.management.ms.entity.Customer;
import com.clearbill.customer.management.ms.exceptions.CustomerNotFoundException;
import com.clearbill.customer.management.ms.mappers.SignUpMapper;
import com.clearbill.customer.management.ms.mappers.UpdateCustomerEntityMapper;
import com.clearbill.customer.management.ms.repository.CustomerRepository;
import com.clearbill.customer.management.ms.security.JwtService;
import com.clearbill.customer.management.ms.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class CustomerServiceImplementation implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImplementation.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SignUpMapper signUpEntityMapper;

    @Autowired
    private UpdateCustomerEntityMapper updateCustomerEntityMapper;

    @Autowired
    private JwtService jwtService;


    @Override
    public SignUpResponseDTO getCustomerById(String customerId) {

        Optional<Customer> customerDetails = customerRepository.findById(customerId);

        if(customerDetails.isEmpty()) throw new CustomerNotFoundException("Customer Not Found!!");

        return signUpEntityMapper.CustomerEntityToSignUpDTO(customerDetails.get());
    }



    @Override
    public SignUpResponseDTO updateCustomerDetails(String customerId, UpdateCustomerRequestDTO updateCustomerRequestDTO) throws IllegalAccessException {

        Optional<Customer> customerDetails = customerRepository.findById(customerId);

        if(customerDetails.isEmpty()) throw new CustomerNotFoundException("Customer Not Found!!");


//        Customer customerDetailsTobeUpdated = updateCustomerEntityMapper.updateCustomerRequestDTOtoCustomerEntity(updateCustomerRequestDTO);

        Map<Object,Object> updates = new HashMap<>();

        Class<?> clazzUpdateCustomerRequestDTO = updateCustomerRequestDTO.getClass();

        Field[] fields = clazzUpdateCustomerRequestDTO.getDeclaredFields();

        for(Field field :fields){

            if(field.get(updateCustomerRequestDTO)!=null){

                updates.put(field.getName(),field.get(updateCustomerRequestDTO));
            }
        }

        Class<?> customerDetailsClass = customerDetails.get().getClass();

        Field[] fieldsCustomer = customerDetailsClass.getDeclaredFields();

        for(Field field :fieldsCustomer){
            field.setAccessible(true);
            if(updates.containsKey(field.getName())){
                field.set(customerDetails.get(),updates.get(field.getName()));
            }
        }


        return signUpEntityMapper.CustomerEntityToSignUpDTO(customerRepository.save(customerDetails.get()));
    }

    @Override
    public SignUpResponseDTO queryCustomerByUniqueParams(HashMap<Object, Object> params, String authToken) {


        String userName= jwtService.getUsernameFromToken(authToken.substring(7));

        Optional<Customer> customerDetails = customerRepository.findByEmail(userName);

        if(customerDetails.isEmpty()) throw new CustomerNotFoundException("Customer Not Found!!");

        return signUpEntityMapper.CustomerEntityToSignUpDTO(customerDetails.get());


    }

    @Override
    public CustomerResposeDTOInternalServices queryCustomerByUniqueParamsForInternalServices(HashMap<Object, Object> params) {



        Optional<Customer> customerDetails =Optional.ofNullable(null);

        if(customerDetails.isEmpty() && params.containsKey("email")) customerDetails= customerRepository.findCustomerByEmailForInternalServices((String) params.get("email"));

        if(customerDetails.isEmpty() && params.containsKey("customerId")) customerDetails = customerRepository.findById((String) params.get("customerId"));


        if(customerDetails.isEmpty()) throw new CustomerNotFoundException("Customer Not Found!!");

        LOGGER.info("Customer :"+customerDetails.get().id);


        return signUpEntityMapper.CustomerEntityToCustomerDTO(customerDetails.get());


    }

    @Override
    public List<CustomerResposeDTOInternalServices> queryCustomersByUniqueParamsForInternalServices(Iterable<String> params, HashMap<String,Object> queryParams) {

       List<Customer> customerDetails = new ArrayList<>();

        if(params!=null && params.iterator().hasNext())customerDetails = customerRepository.findAllById(params);
        else if(queryParams.containsKey("requestedCustomerId") && queryParams.containsKey("targetCustomerEmail")){
            customerDetails = customerRepository.findCustomersByEmailOrId((String) queryParams.get("requestedCustomerId"), (String) queryParams.get("targetCustomerEmail"));
        }

        if(customerDetails.size()==0) throw new CustomerNotFoundException("Customer Not Found!!");


        return signUpEntityMapper.ListOfCustomerEntityToCustomerDTO(customerDetails);

    }


}
