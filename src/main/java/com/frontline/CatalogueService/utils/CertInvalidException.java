package com.frontline.CatalogueService.utils;

public class CertInvalidException extends Exception {
    public CertInvalidException(String errorMessage){
        super(errorMessage);
    }
}
