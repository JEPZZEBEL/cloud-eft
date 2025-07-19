package cl.duoc.azuread.ejemplo.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class TokenUtil {

    private Jwt getJwt() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Jwt jwt) {
            return jwt;
        }
        return null;
    }

    public String getEmail() {
        Jwt jwt = getJwt();
        return jwt != null ? jwt.getClaimAsString("emails") : null;
    }

    public String getName() {
        Jwt jwt = getJwt();
        return jwt != null ? jwt.getClaimAsString("name") : null;
    }

    public String getRUT() {
        Jwt jwt = getJwt();
        return jwt != null ? jwt.getClaimAsString("extension_RUT") : null;
    }

    public String getRoles() {
        Jwt jwt = getJwt();
        return jwt != null ? jwt.getClaimAsString("extension_Roles") : null;
    }
}
