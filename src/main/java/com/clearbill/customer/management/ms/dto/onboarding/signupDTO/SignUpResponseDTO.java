package com.clearbill.customer.management.ms.dto.onboarding.signupDTO;

import java.util.List;


public class SignUpResponseDTO {

    public String id;
    public String email;
    public String name;
    public String contact;
    public String country;
    public String profilePicture;
    public String currency;
    public List<String> preferred_languages;
    public boolean isMobileVerified;
    public boolean isEmailVerified;
    public String createdOn;
    public String updatedOn;

    @Override
    public String toString() {
        return "SignUpResponseDTO{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", country='" + country + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", currency='" + currency + '\'' +
                ", preferred_languages=" + preferred_languages +
                ", isMobileVerified=" + isMobileVerified +
                ", isEmailVerified=" + isEmailVerified +
                ", createdOn='" + createdOn + '\'' +
                ", updatedOn='" + updatedOn + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedOn() {
        return createdOn;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

}
