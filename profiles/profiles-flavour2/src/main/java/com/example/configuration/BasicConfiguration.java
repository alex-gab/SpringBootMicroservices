package com.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class BasicConfiguration {
    @Value("${configuration.projectName}")
    public void setProjectName(final String projectName) {
        System.out.println("setting project name: " + projectName);
    }

    @Autowired
    public void setEnvironment(final Environment env) {
        System.out.println("setting environment: " + env.getProperty("configuration.projectName"));
    }

    @Autowired
    public void setConfigurationProjectProperties(final ConfigurationProjectProperties cp) {
        System.out.println("setting setConfigurationProjectProperties: " + cp.getProjectName());
    }
}
