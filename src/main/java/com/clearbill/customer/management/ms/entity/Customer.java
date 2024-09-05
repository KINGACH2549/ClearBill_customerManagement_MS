package com.clearbill.customer.management.ms.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import java.util.List;

@Document
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    public String id;

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", country='" + country + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", currency='" + currency + '\'' +
                ", preferred_languages=" + preferred_languages +
                ", password='" + password + '\'' +
                ", isMobileVerified=" + isMobileVerified +
                ", isEmailVerified=" + isEmailVerified +
                ", createdOn='" + createdOn + '\'' +
                ", updatedOn='" + updatedOn + '\'' +
                '}';
    }

    public String email;
    public String name;
    public String contact;
    public String country;
    public String profilePicture;
    public String currency;
    public List<String> preferred_languages;
    public String password;
    public boolean isMobileVerified;
    public boolean isEmailVerified;
    public String createdOn;
    public String updatedOn;


    public String getId() {
        return id;
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
