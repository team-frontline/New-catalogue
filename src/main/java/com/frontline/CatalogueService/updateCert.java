package com.frontline.CatalogueService;

import org.frontline.certInstaller.UpdateKeyStore;

import java.util.HashMap;
import java.util.Map;

public class updateCert {
    public static void main(String[] args) {
        String oldKeyfile = "frtmscatalogue-new1";
        String newKeyFile = "frtmscatalogue-new2";
        String keystoreName = "frtmscatalogue.jks";
        String aliasName = "frtmscatalogue.com";
        String password = "pass123";

        Map<String, String> csrParamMap = new HashMap<String, String>();
        csrParamMap.put("commonName", "frtmscatalogue.com");
        csrParamMap.put("organizationUnit", "MSChain");
        csrParamMap.put("organizationName", "Frontline.org");
        csrParamMap.put("localityName", "Colombo");
        csrParamMap.put("stateName", "West");
        csrParamMap.put("country", "SL");
        try {
            UpdateKeyStore.installNewCert("src/main/resources", oldKeyfile, newKeyFile, csrParamMap,
                    keystoreName, aliasName, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public static class InitiateKeyStore {
        public static void main(String[] args) {

        }
    }
}
