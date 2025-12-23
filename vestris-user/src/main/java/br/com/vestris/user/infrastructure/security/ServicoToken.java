package br.com.vestris.user.infrastructure.security;

import br.com.vestris.user.domain.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class ServicoToken {
    // Em produção, isso deve vir do application.properties!
    // Para o MVP, usaremos uma chave fixa segura gerada aqui.
    private static final Key CHAVE_SECRETA = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRACAO_MS = 86400000; // 1 dia (24h)

    public String gerarToken(Usuario usuario) {
        Date agora = new Date();
        Date dataExpiracao = new Date(agora.getTime() + EXPIRACAO_MS);

        return Jwts.builder()
                .setSubject(usuario.getId().toString()) // ID do usuário é o "assunto"
                .claim("email", usuario.getEmail())
                .claim("perfil", usuario.getPerfil().name())
                .setIssuedAt(agora)
                .setExpiration(dataExpiracao)
                .signWith(CHAVE_SECRETA)
                .compact();
    }

    // Futuramente faremos o validarToken aqui
}
