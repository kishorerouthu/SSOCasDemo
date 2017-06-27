package com.css.sso.cas;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;

/**
 * @author Kishore Routhu on 25/6/17 12:22 PM.
 */
public class SSOCasAuthenticationEntryPoint extends CasAuthenticationEntryPoint {

    @Autowired
    @Qualifier("casServiceProperties")
    private ServiceProperties serviceProperties;

    protected String createServiceUrl(HttpServletRequest request, HttpServletResponse response) {
        String service = this.serviceProperties.getService();
        if (service.startsWith("/")) {
            String serviceUrl = getServiceUrl(request);
            service = serviceUrl + "/j_spring_cas_security_check";
            serviceProperties.setService(service);
        }

        return CommonUtils.constructServiceUrl(request, response, service, null, this.serviceProperties.getServiceParameter(),
                this.serviceProperties.getArtifactParameter(), true);
    }

    public static String getServiceUrl(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();
        String contextPath = request.getContextPath();

        String serviceHostUrl = requestURL.substring(0, requestURL.lastIndexOf(contextPath));
        String serviceUrl = serviceHostUrl + contextPath;
        return serviceUrl;
    }

}
