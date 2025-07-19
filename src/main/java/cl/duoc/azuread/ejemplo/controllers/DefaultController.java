package cl.duoc.azuread.ejemplo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class DefaultController {

	@PostMapping("/mensaje")
	public String mensaje(@AuthenticationPrincipal Jwt jwt) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Authorities: " + auth.getAuthorities());

		String roles = jwt.getClaimAsString("extension_Roles");

		System.out.println("Backend llamado, roles: " + roles);
		return "{\"mensaje\": \"Integración OK al backend, claim roles: " + roles + "\"}";
	}

	@PostMapping("/mensaje-admin")
	public String mensajeAdmin(@AuthenticationPrincipal Jwt jwt) {

		String roles = jwt.getClaimAsString("extension_Roles");

		System.out.println("Backend ADMIN llamado, roles: " + roles);
		return "{\"mensaje\": \"Integración OK al backend ADMIN, claim roles: " + roles + "\"}";
	}
}
