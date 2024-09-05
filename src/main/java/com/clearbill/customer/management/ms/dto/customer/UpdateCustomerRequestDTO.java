package com.clearbill.customer.management.ms.dto.customer;

import java.util.List;

public class UpdateCustomerRequestDTO {

    public String email;
    public String name;
    public String contact;
    public String country;
    public String profilePicture;
    public String currency;
    public List<String> preferred_languages;
    public boolean isMobileVerified;

    @Override
    public String toString() {
        return "UpdateCustomerRequestDTO{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", country='" + country + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", currency='" + currency + '\'' +
                ", preferred_languages=" + preferred_languages +
                ", isMobileVerified=" + isMobileVerified +
                ", isEmailVerified=" + isEmailVerified +
                '}';
    }

    public boolean isEmailVerified;
}
