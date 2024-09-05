package com.clearbill.customer.management.ms.dto.customer;


public class CustomerResposeDTOInternalServices {
    public String id;
    public String name;
    public String profilePicture;
    public String createdOn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "CustomerResposeDTOInternalServices{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}
