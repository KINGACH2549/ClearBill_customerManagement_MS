package com.clearbill.customer.management.ms.controller;

import com.clearbill.customer.management.ms.dto.onboarding.loginDTO.LoginRequestDTO;
import com.clearbill.customer.management.ms.dto.onboarding.loginDTO.LoginResponseDTO;
import com.clearbill.customer.management.ms.dto.onboarding.signupDTO.SignUpRequestDTO;
import com.clearbill.customer.management.ms.dto.onboarding.signupDTO.SignUpResponseDTO;
import com.clearbill.customer.management.ms.service.OnboardingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customerManagement/open")
public class Onboarding {

  private static final  Logger LOGGER = LoggerFactory.getLogger(Onboarding.class);


  @Autowired
  private OnboardingService onboardingService;

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponseDTO> customerSignUp(@RequestBody SignUpRequestDTO signUpRequestDTO) throws IllegalAccessException {
      LOGGER.info("Request Recieved for Signup");
      LOGGER.trace(signUpRequestDTO.toString());


      SignUpResponseDTO response= onboardingService.signUpNewCustomer(signUpRequestDTO);

      LOGGER.info("Response from signUp service");
      LOGGER.trace(response.toString());

      return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> loginCustomer(@RequestBody LoginRequestDTO loginRequestDTO) throws IllegalAccessException {

    LOGGER.info("Request Recieved for Customer Login and Token generation: ");
    LOGGER.trace(loginRequestDTO.toString());


    LoginResponseDTO response= onboardingService.loginExistingCustomer(loginRequestDTO);

    LOGGER.info("Response from Login service");

    return new ResponseEntity<>(response, HttpStatus.OK);
  }


}
