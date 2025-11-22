
package com.digis.IHernandezProgramacionNCapas.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final Key key = Keys.hmacShaKeyFor("f9Jr4L1x!aP$6mQz7VkB#9cD2hE*3nTqU@8yZrW%5oXj&1bH+KfG^0sLpMvNtY".getBytes());

    public String generarToken(int idUsuario) {
        return Jwts.builder()
                .setSubject(String.valueOf(idUsuario))
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    
    
    public int obtenerIdUsuario(String token) {
        return Integer.parseInt(Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }

    public boolean esTokenValido(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
