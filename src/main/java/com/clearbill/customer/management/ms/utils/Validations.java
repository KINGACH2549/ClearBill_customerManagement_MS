package com.clearbill.customer.management.ms.utils;



import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;


public class Validations<T> {

    public List<String> error;
    public List<String> resolution;

    public List<String> getError() {
        return error;
    }



    public List<String> getResolution() {
        return resolution;
    }

    boolean isValid;

    private static final String emailPattern  = "^([a-zA-Z0-9_\\-\\;.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";


    T entity;

    public Validations() {
        this.isValid = true;
        error  = new ArrayList<>();
        resolution = new ArrayList<>();
    }

    public T getEntity() {
        return entity;
    }

    public Validations<T> setEntity(T entity) {
        this.entity = entity;
        return this;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Validations<T> isNotNull(Object validationInput){
          if(validationInput==null){
              this.setValid(false);
          }
          return this;
    }

    public Validations<T> checkEntityMembersNotNull(HashSet<?> members) throws IllegalAccessException {

        Class<?> clazz = entity.getClass();

        Field[] fields = clazz.getDeclaredFields();

        for(Field field :fields){

            if(members.contains(field.getName())){
                if(field.get(entity)==null){
                    error.add("Field "+field.getName()+" is null");
                    resolution.add("Field "+field.getName()+" can not be null . Provide some valid value");
                    this.setValid(false);
                }
            }
        }
        return this;
    }

    public Validations<T> isContactNumberValid(String country , String contactNumber){


        if(contactNumber==null || contactNumber.length()!=10){
            error.add("Field Contact number is not valid!!");
            resolution.add("Contact Number should have 10 digits for country India!!");
            this.setValid(false);
        }

        return this;
    }

    public Validations<T> isEmailValid(String email){
        if(email==null) return this;
        // Compile the pattern
        Pattern pattern = Pattern.compile(emailPattern);

        // Test if an email matches the pattern
        boolean isEmailValid = pattern.matcher(email).matches();

        if(!isEmailValid) {
            error.add("Field Email is not valid!!");
            resolution.add("Email format is incorrect !!!.Correct format is xyz@clearbill.com");

            this.setValid(false);
        }
        return this;
    }
}
