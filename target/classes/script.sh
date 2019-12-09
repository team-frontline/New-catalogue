#!/bin/bash
keytool -import -trustcacerts -alias ca1.com -file certCA1.pem -keystore frtmscatalogue.jks

#keytool -import -trustcacerts -alias ms1.org  -file ms1_cert.pem -keystore ms2-store1.jks
#
#keytool -list -v -keystore ms2-store1.jks
#
#keytool -importkeystore -srckeystore ms2-store1.jks -destkeystore ms2-store1.p12 -srcstoretype JKS -deststoretype PKCS12 -deststorepass pass123
