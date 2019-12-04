package com.frontline.CatalogueService;

import org.frontline.certInstaller.mschainOperation.CertManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class CatalogueServiceApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(CatalogueServiceApplication.class, args);

		/* String commonName, String organizationUnit, String organizationName,
         String localityName, String stateName, String country */

        Map<String, String> csrParamMap = new HashMap<String, String>();
        csrParamMap.put("commonName", "frontline.catalogue");
        csrParamMap.put("organizationUnit", "MSChain");
        csrParamMap.put("organizationName", "Frontline.org");
        csrParamMap.put("localityName", "Colombo");
        csrParamMap.put("stateName", "West");
        csrParamMap.put("country", "SL");


        try {
            CertManager certManager = new CertManager();
            certManager.initiateCertificate("src/main/resources", "catalogue123", csrParamMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
