package com.frontline.CatalogueService.utils;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

public class MSchainCertHandler {
    public  static boolean validateCertificate(String cn, X509Certificate x509Certificate) throws IOException, CertificateEncodingException {
        RestTemplate restTemplate = new RestTemplate();

        String certString = CertX509Handler.stringFromCert(x509Certificate);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("subjectName", cn);
        map.add("cert",certString);
        System.out.println("====================================================================================");
        System.out.println(x509Certificate.toString());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<JsonNode> response = restTemplate.postForEntity( "http://18.232.207.225:3000/api/eval", request , JsonNode.class );
        boolean validity = response.getBody().get("data").get("validity").asBoolean();
        System.out.println("from mschain validity checker");
        System.out.println(response.getBody().get("data").get("validity"));
        System.out.println(response.getBody().get("sentCert"));
        System.out.println(response.getBody().get("data").get("certString"));
        System.out.println(certString.equals(response.getBody().get("sentCert")));
        if(certString.equals(response.getBody().get("sentCert"))){
            System.out.println("certs are equal");
        }else{
            System.out.println("certs are not equal");
        }

        if(validity){
            return true;
        }else{
            return false;
        }
    }
}
