package com.clearbill.customer.management.ms.service.implementations;

import com.clearbill.customer.management.ms.dto.onboarding.loginDTO.LoginRequestDTO;
import com.clearbill.customer.management.ms.dto.onboarding.loginDTO.LoginResponseDTO;
import com.clearbill.customer.management.ms.dto.onboarding.signupDTO.SignUpRequestDTO;
import com.clearbill.customer.management.ms.dto.onboarding.signupDTO.SignUpResponseDTO;
import com.clearbill.customer.management.ms.exceptions.ValidationException;
import com.clearbill.customer.management.ms.mappers.LoginEntityMapper;
import com.clearbill.customer.management.ms.mappers.SignUpMapper;
import com.clearbill.customer.management.ms.repository.CustomerRepository;
import com.clearbill.customer.management.ms.security.JwtService;
import com.clearbill.customer.management.ms.service.OnboardingService;
import com.clearbill.customer.management.ms.utils.TimeUtils;
import com.clearbill.customer.management.ms.utils.Validations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class OnboardingServiceImplementation implements OnboardingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OnboardingServiceImplementation.class);

    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    private SignUpMapper signUpEntityMapper;

    @Autowired
    private LoginEntityMapper loginEntityMapper;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtService jwtService;



    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {

            manager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    private   void validateSignUpRequest(SignUpRequestDTO signUpRequestDTO) throws IllegalAccessException ,IllegalArgumentException{
        // name , email , contact , password , currency
        Validations<SignUpRequestDTO> signUpRequestDTOValidations = new Validations<>();
        HashSet<String> validateMembers = new HashSet<>();

        validateMembers.add("name");
        validateMembers.add("email");
        validateMembers.add("contact");
        validateMembers.add("password");

        boolean isRequestValid =  signUpRequestDTOValidations.setEntity(signUpRequestDTO).checkEntityMembersNotNull(validateMembers).isContactNumberValid("",signUpRequestDTO.getContact()).isEmailValid(signUpRequestDTO.getEmail()).isValid();

        if(!isRequestValid){
            ValidationException validationException = new ValidationException("Validation Exception!!!");
            validationException.setErrorList(signUpRequestDTOValidations.getError());
            validationException.setResolutionList(signUpRequestDTOValidations.getResolution());
            throw validationException;
        }
    }

    private void validateLoginRequest(LoginRequestDTO loginRequestDTO) throws IllegalAccessException,IllegalArgumentException{
        // email , password , contact

        boolean isRequestValid = false;
        Validations<LoginRequestDTO> loginRequestDTOValidations = new Validations<>();
        HashSet<String> validateMembers = new HashSet<>();
        validateMembers.add("password");
        loginRequestDTOValidations = loginRequestDTOValidations.setEntity(loginRequestDTO).checkEntityMembersNotNull(validateMembers);

        if(loginRequestDTO.getEmail()!=null){
            isRequestValid =  loginRequestDTOValidations.isEmailValid(loginRequestDTO.getEmail()).isValid();
        }else if(loginRequestDTO.getContact()!=null){
            isRequestValid =  loginRequestDTOValidations.isEmailValid(loginRequestDTO.getEmail()).isValid();
        }

        if(!isRequestValid){
            ValidationException validationException = new ValidationException("Validation Exception!!!");
            validationException.setErrorList(loginRequestDTOValidations.getError());
            validationException.setResolutionList(loginRequestDTOValidations.getResolution());
            throw validationException;
        }


    }


    @Override
    public SignUpResponseDTO signUpNewCustomer(SignUpRequestDTO signUpRequestDTO) throws IllegalAccessException,IllegalArgumentException {


        LOGGER.info("Validating Request For SignUp!!");

        validateSignUpRequest(signUpRequestDTO);

        LOGGER.info("Validation Done for Sign Up!! No issues Found!!!");

        //Time related operations
        TimeUtils timeUtils = new TimeUtils();
        String currentTime = timeUtils.getCurrentTime();

        signUpRequestDTO.setCreatedOn(currentTime);
        signUpRequestDTO.setUpdatedOn(currentTime);

        LOGGER.info("Customer created at " + currentTime);


        return signUpEntityMapper.CustomerEntityToSignUpDTO(customerRepository.save(signUpEntityMapper.signUpDTOtoCustomerEntity(signUpRequestDTO)));


    }

    @Override
    public LoginResponseDTO loginExistingCustomer(LoginRequestDTO loginRequestDTO) throws IllegalAccessException, IllegalArgumentException {
        LOGGER.info("Validating Request For Login!!");

        validateLoginRequest(loginRequestDTO);

        LOGGER.info("Validation Done for Login!! No issues Found!!!");

        this.doAuthenticate(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        LOGGER.info("Authenticated User ....");

        UserDetails userDetails = loginService.loadUserByUsername(loginRequestDTO.getEmail());
        LOGGER.info(userDetails.toString());

        String token = this.jwtService.generateToken(userDetails);



        LOGGER.trace("Token Generated for user :"+userDetails.getUsername());

        return loginEntityMapper.returnLoginToken(token);
    }

}
