package com.cos.security1.config;

import java.io.InputStream;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.KeycloakDeploymentBuilder;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }
    
    /*@Bean
    public KeycloakConfigResolver keycloakConfigResolver() {
    	return new KeycloakConfigResolver() {
    		private KeycloakDeployment keycloakDeployment;
    		@Override
    		public KeycloakDeployment resolve(HttpFacade.Request facade) {
    			if (keycloakDeployment != null) {
    				return keycloakDeployment;
    			}
    			InputStream configInputStream = getClass().getResourceAsStream("/keycloak.json");
    			return KeycloakDeploymentBuilder.build(configInputStream);
    		}
    	};
    }*/
}