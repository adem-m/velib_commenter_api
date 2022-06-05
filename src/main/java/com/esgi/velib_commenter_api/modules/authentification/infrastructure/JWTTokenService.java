package com.esgi.velib_commenter_api.modules.authentification.infrastructure;

import com.esgi.velib_commenter_api.modules.authentification.domain.Token;
import com.esgi.velib_commenter_api.modules.authentification.domain.TokenService;
import com.esgi.velib_commenter_api.modules.authentification.domain.TokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JWTTokenService implements TokenService {
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    private static final int SEVEN_DAYS = 1000 * 60 * 60 * 24 * 7;
    private static final int EXPIRATION_TIME = SEVEN_DAYS;

    @Override
    public Token generateToken(String email, String userId) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(email)
                .setIssuer(userId)
                .signWith(signatureAlgorithm, signingKey);

        long expMillis = nowMillis + EXPIRATION_TIME;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);

        log.info("Token creation");
        return new Token(builder.compact());
    }

    @Override
    public String getUserId(Token token) {
        Claims body = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(TokenUtils.getTokenValue(token)).getBody();
        return body.getIssuer();
    }
}
