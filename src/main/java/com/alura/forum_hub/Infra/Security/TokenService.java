package com.alura.forum_hub.Infra.Security;

import com.alura.forum_hub.Domain.Usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.expiration}")
    private Long expiration;


    public String gerarToken(Usuario usuario) {
        Date dataAtual = new Date();
        Date dataExpiration = new Date(dataAtual.getTime() + expiration);
        try {
            RSAPublicKey rsaPublicKey;
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Forum Hub")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiration)
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    public Optional<String> getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return Optional.ofNullable(
                    JWT.require(algoritmo)
                            .withIssuer("Forum Hub")
                            .build()
                            .verify(tokenJWT)
                            .getSubject()
            );
        } catch (JWTVerificationException exception) {
            return Optional.empty();
        }
    }
}
