package com.frontline.CatalogueService.Interceptor;

import com.frontline.CatalogueService.utils.CertInvalidException;
import com.frontline.CatalogueService.utils.CertX509Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;;

@Component
public class ControllerInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws IOException {
        boolean certVerified = false;
        HandlerMethod method = (HandlerMethod) handler;
        MethodParameter[] parms = method.getMethodParameters();
        for (MethodParameter parm : parms) {
            logger.info(parm.getNestedGenericParameterType().getTypeName());
        }

        X509Certificate[] certs = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");

        try {
            certVerified = CertX509Handler.validateCertificate(certs);
//            request.setAttribute("certVerified",certVerified);
        } catch (Exception e) {
            response.setStatus(400);    //chrome displays its generic page for 400l
            response.setHeader("Error", e.getMessage());

        }

        return certVerified;
    }

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        logger.info("this is interceptor, postHandle method");
//        response.addHeader("Cert",CertX509Handler.stringFromCert(serverCertificate));
    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.info("this is interceptor, afterCompletion method");
        String h = response.getHeader("Cert");
        System.out.println(h+"================================================");
    }
}
