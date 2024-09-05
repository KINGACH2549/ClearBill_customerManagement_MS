package com.clearbill.customer.management.ms.exceptions;

import java.util.List;

public class ValidationException extends IllegalArgumentException{

    public List<String> errorList;
    public List<String> resolutionList;

    public  ValidationException(String message){
        super(message);
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getResolutionList() {
        return resolutionList;
    }

    public void setResolutionList(List<String> resolutionList) {
        this.resolutionList = resolutionList;
    }
}
