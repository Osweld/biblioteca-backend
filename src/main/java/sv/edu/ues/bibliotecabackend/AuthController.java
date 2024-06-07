package sv.edu.ues.bibliotecabackend;

import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.ues.bibliotecabackend.auth.models.AuthUser;
import sv.edu.ues.bibliotecabackend.auth.services.JWTService;
import sv.edu.ues.bibliotecabackend.auth.services.JWTServiceImpl;
import sv.edu.ues.bibliotecabackend.service.UsuarioService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JWTService jwtService;
    private final UsuarioService usuarioService;

    public AuthController(JWTService jwtService, UsuarioService usuarioService) {
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/token/refresh")
    @PermitAll()
    ResponseEntity<Map<String,Object>> refreshToken(HttpServletRequest request) throws IOException {
        Map<String, Object> body = new HashMap<>();
        String header = request.getHeader(JWTServiceImpl.HEADER_STRING);
        if (!jwtService.requiresAuthentication(header) || !jwtService.validate(header)) {
            body.put("error", "there was an error refreshing token");
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);

        }
        AuthUser user = new AuthUser( jwtService.getId(header),jwtService.getUsername(header),"",true,true,true,true,jwtService.getAuthorities(header));
        String token = jwtService.refreshToken(user);
        body.put("user",user);
        body.put("token",token);
        return new ResponseEntity<>(body,HttpStatus.OK);
    }
}
