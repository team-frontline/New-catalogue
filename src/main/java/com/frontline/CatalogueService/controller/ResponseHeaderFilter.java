package com.frontline.CatalogueService.controller;

import com.frontline.CatalogueService.utils.CertX509Handler;
import com.sun.net.httpserver.HttpExchange;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@WebFilter("/api/item/*")
public class ResponseHeaderFilter implements Filter {
    private X509Certificate serverCertificate = CertX509Handler.loadCertFromKeyStore();

    public ResponseHeaderFilter() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try {
            String certString = CertX509Handler.stringFromCert(serverCertificate);
            System.out.println(certString);
            httpServletResponse.setHeader(
                    "Cert",certString);
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // ...
    }

    @Override
    public void destroy() {
        // ...
    }
}
