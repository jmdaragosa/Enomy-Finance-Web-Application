package com.enomy.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null; // All config is in AppConfig
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" }; // Map all requests to DispatcherServlet
    }
}
