package br.com.vestris.user.infrastructure.security;

import br.com.vestris.user.domain.model.Usuario;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class ServicoToken {
    // Em produção, use application.properties. Para MVP, chave fixa.
    private static final Key CHAVE_SECRETA = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRACAO_MS = 86400000; // 24h

    public String gerarToken(Usuario usuario) {
        Date agora = new Date();
        Date dataExpiracao = new Date(agora.getTime() + EXPIRACAO_MS);

        JwtBuilder builder = Jwts.builder()
                .setSubject(usuario.getId().toString())
                .claim("email", usuario.getEmail())
                .claim("perfil", usuario.getPerfil().name())
                .claim("scope", usuario.getScope() != null ? usuario.getScope().name() : "TENANT");

        // --- CORREÇÃO: EMBUTIR A CLÍNICA NO TOKEN PARA O FRONTEND VER ---
        if (usuario.getClinica() != null) {
            builder.claim("clinicaId", usuario.getClinica().getId().toString());
        }

        return builder
                .setIssuedAt(agora)
                .setExpiration(dataExpiracao)
                .signWith(CHAVE_SECRETA)
                .compact();
    }
}
