package pers.yan.video.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import pers.yan.video.common.common.ResponseCode;
import pers.yan.video.common.exception.ApiRuntimeException;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author likaiyan
 * @date 2020/7/6 3:12 下午
 */
@Component
public class JwtTokenUtil {

    private static final String CLAIM_KEY_SUB = "sub";

    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(getExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String generateToken(int userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_SUB, userId);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    public String refreshToken(String oldToken) {
        if (StringUtils.isEmpty(oldToken) || !validateToken(oldToken)) {
            throw new ApiRuntimeException(ResponseCode.VALIDATE_FAILED);
        }
        int userId = getUserIdFromToken(oldToken);
        return generateToken(userId);
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return new Date().after(getExpirationDateFromToken(token));
    }

    public Date getExpirationDate() {
        return Date.from(Instant.now().plusMillis(1000 * expiration));
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public Date getCreateDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return (Date) claims.get(CLAIM_KEY_CREATED);
    }

    public Date getExpirationDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    public int getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return (int) claims.get(CLAIM_KEY_SUB);
    }

    public String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(tokenHeader);
        if (StringUtils.isEmpty(bearerToken) || bearerToken.length() < tokenHead.length()) {
            return null;
        }
        return bearerToken.substring(tokenHead.length());
    }

    public String getToken(ServerHttpRequest request) {
        List<String> bearerTokens = request.getHeaders().getOrEmpty(tokenHeader);
        if (CollectionUtils.isEmpty(bearerTokens) || bearerTokens.get(0).length() < tokenHead.length()) {
            return null;
        }
        return bearerTokens.get(0).substring(tokenHead.length());
    }


}
