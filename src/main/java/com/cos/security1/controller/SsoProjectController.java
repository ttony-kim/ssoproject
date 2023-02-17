package com.cos.security1.controller;

import java.security.Principal;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.IDToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SsoProjectController {
	
	@GetMapping("/permit-all")
	public ResponseEntity<String> permit() {
		return ResponseEntity.ok("welcome");
	}
	
	@GetMapping("/authenticated")
	public ResponseEntity<String> authenticated() {
		return ResponseEntity.ok("login success");
	}
	
	@GetMapping("/auth-admin")
	public ResponseEntity<String> authAdmin() {
		
		return ResponseEntity.ok("Admin Page! <" + getUserName() + ">");
	}
	
	@GetMapping("/auth-user")
	public ResponseEntity<String> authUser() {
		return ResponseEntity.ok("User Page! <" + getUserName() + ">");
	}
	
	private void getIdToken(Model model) {
		KeycloakAuthenticationToken authentication = 
			      (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

	    Principal principal = (Principal) authentication.getPrincipal();

	    String userIdByToken = "";

	    if (principal instanceof KeycloakPrincipal) {
	        KeycloakPrincipal<KeycloakSecurityContext> kPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;
	        IDToken token = kPrincipal.getKeycloakSecurityContext().getIdToken();
	        userIdByToken = token.getSubject();
	    }

	    model.addAttribute("userIDByToken", userIdByToken);
	}
	
	private String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		KeycloakPrincipal principal = (KeycloakPrincipal)auth.getPrincipal();
		
		KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
		AccessToken accessToken = session.getToken();
		String username = accessToken.getPreferredUsername();
//		String emailID = accessToken.getEmail();
//		String lastname = accessToken.getFamilyName();
//		String firstname = accessToken.getGivenName();
//		String realmName = accessToken.getIssuer();
//		AccessToken.Access realmAccess = accessToken.getRealmAccess();
	
		return username;
	}
}
