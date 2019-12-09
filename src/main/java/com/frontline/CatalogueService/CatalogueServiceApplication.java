package com.frontline.CatalogueService;

import com.frontline.CatalogueService.config.Config;
import org.frontline.certInstaller.certOperation.KeyStoreManager;
import org.frontline.certInstaller.mschainOperation.CertManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ServletComponentScan
@SpringBootApplication
public class CatalogueServiceApplication {

    public static void main(String[] args) throws IOException {

		/* String commonName, String organizationUnit, String organizationName,
         String localityName, String stateName, String country */
        Map<String, String> csrParamMap = new HashMap<String, String>();
        csrParamMap.put("commonName", "frtmscatalogue.com");
        csrParamMap.put("organizationUnit", "MSChain");
        csrParamMap.put("organizationName", "Frontline.org");
        csrParamMap.put("localityName", "Colombo");
        csrParamMap.put("stateName", "West");
        csrParamMap.put("country", "SL");
        Config.setKeyStoreName("mscatalogue.jks");
        Config.setKeyStorePassword("pass123");


        try {
            File ks = new File("src/main/resources/frtmscatalogue.jks");
            if(!ks.exists()){
                CertManager certManager = new CertManager();
                certManager.initiateCertificate("src/main/resources", "frtmscatalogue", csrParamMap);
                KeyStoreManager.installCert("src/main/resources",certManager.getKeyPair()
                        ,"fmscatalogue.com","pass123",certManager.getCertificate(),"fmscatalogue.jks");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            SpringApplication.run(CatalogueServiceApplication.class, args);
        }

    }

}
