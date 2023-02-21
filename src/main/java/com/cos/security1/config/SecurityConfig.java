package com.cos.security1.config;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
//        auth.authenticationProvider(keycloakAuthenticationProvider());
    }
    
    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(buildSessionRegistry());
    }

    @Bean
    protected SessionRegistry buildSessionRegistry() {
        return new SessionRegistryImpl();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        super.configure(http);
        http.authorizeRequests()
			.antMatchers("/permit-all").permitAll()
			.antMatchers("/authenticated").authenticated()
			.antMatchers("/auth-admin").hasAnyRole("ADMIN")
			.antMatchers("/auth-user").hasAnyRole("USER")
			.anyRequest().permitAll();
        http.csrf().disable();
    }
    
//  @Override
//  public void configure(WebSecurity web) {
//      web.ignoring()
//              .antMatchers("/favicon.ico", "/**/favicon.ico");
//
//      web.ignoring()
//              .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//  }
    
//    @Bean
//    @Override
//    protected KeycloakAuthenticationProcessingFilter keycloakAuthenticationProcessingFilter() throws Exception {
//        KeycloakAuthenticationProcessingFilter filter = new KeycloakAuthenticationProcessingFilter(authenticationManagerBean());
//        filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy());
//        filter.setAuthenticationSuccessHandler(successHandler());
//        return filter;
//    }
//
//    @Bean
//    public KeycloakAuthenticationSuccessHandler successHandler() {
//        return new KeycloakAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler());
//    }
    
}

