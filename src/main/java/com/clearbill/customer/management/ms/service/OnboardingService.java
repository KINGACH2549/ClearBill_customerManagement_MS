package com.clearbill.customer.management.ms.service;

import com.clearbill.customer.management.ms.dto.onboarding.loginDTO.LoginRequestDTO;
import com.clearbill.customer.management.ms.dto.onboarding.loginDTO.LoginResponseDTO;
import com.clearbill.customer.management.ms.dto.onboarding.signupDTO.SignUpRequestDTO;
import com.clearbill.customer.management.ms.dto.onboarding.signupDTO.SignUpResponseDTO;


public interface OnboardingService {

     SignUpResponseDTO signUpNewCustomer(SignUpRequestDTO signUpRequestDTO) throws IllegalAccessException,IllegalArgumentException;

     LoginResponseDTO loginExistingCustomer(LoginRequestDTO loginRequestDTO) throws IllegalAccessException,IllegalArgumentException;
}
