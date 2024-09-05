package com.clearbill.customer.management.ms.dto.onboarding.signupDTO;

import java.util.List;

public class SignUpRequestDTO {

    public String email;
    public String name;
    public String contact;
    public String country;
    public String profilePicture;
    public String currency;
    public List<String> preferred_languages;
    public String password;
    public String createdOn;
    public String updatedOn;
    public boolean isMobileVerified;
    public boolean isEmailVerified;

    public String getCreatedOn() {
        return createdOn;
    }

    @Override
    public String toString() {
        return "SignUpRequestDTO{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", country='" + country + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", currency='" + currency + '\'' +
                ", preferred_languages=" + preferred_languages +
                ", password='" + password + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", updatedOn='" + updatedOn + '\'' +
                ", isMobileVerified=" + isMobileVerified +
                ", isEmailVerified=" + isEmailVerified +
                '}';
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public boolean isMobileVerified() {
        return isMobileVerified;
    }

    public void setMobileVerified(boolean mobileVerified) {
        isMobileVerified = mobileVerified;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getPreferred_languages() {
        return preferred_languages;
    }

    public void setPreferred_languages(List<String> preferred_languages) {
        this.preferred_languages = preferred_languages;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
