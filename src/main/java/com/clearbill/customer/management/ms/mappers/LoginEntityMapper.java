package com.clearbill.customer.management.ms.mappers;

import com.clearbill.customer.management.ms.dto.onboarding.loginDTO.LoginResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class LoginEntityMapper {

    public LoginResponseDTO returnLoginToken(String token){

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken(token);
        return  loginResponseDTO;
    }
}
