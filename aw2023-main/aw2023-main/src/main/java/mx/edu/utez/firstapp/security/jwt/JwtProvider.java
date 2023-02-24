package mx.edu.utez.firstapp.security.jwt;


import io.jsonwebtoken.*;
import mx.edu.utez.firstapp.security.model.UserAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    public final static Logger LOGGER = LoggerFactory
            .getLogger(JwtProvider.class);

    //este arroba buecsra propiedades en el el .properties
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    //generar token
    public String generateToken(Authentication authentication){
        UserAuth userAuth = (UserAuth) authentication.getPrincipal();
        return Jwts.builder().setSubject(userAuth.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+expiration*1000L)).signWith(SignatureAlgorithm.ES512,secret).compact();

    }

    //el body es el payload del token
    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJwt(token);
            return true;
        }
        catch (MalformedJwtException e){
            LOGGER.error("Token mal formado");
        }catch (UnsupportedJwtException e){
            LOGGER.error("Token no sportado");
        }catch (ExpiredJwtException e){
            LOGGER.error("Token expirado");
        }catch (IllegalArgumentException e){
            LOGGER.error("Token no provisto");
        }catch (SignatureException e){
            LOGGER.error("Error en la firma del token");
        }
        return false;
    }
}
