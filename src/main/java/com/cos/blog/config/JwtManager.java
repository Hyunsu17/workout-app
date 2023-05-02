package com.cos.blog.config;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class JwtManager {
    private final String securityKey= "CAPSTONE";
    private final String exTimeForRefresh= "";
    private final String exTimeForAcess= "";

    /**
     * RefreshToken 발급
     *
     * @param user
     * @return String RefreshToken
     */
    public String generateRefreshToken(User user){
        return generateJwtToken(user, exTimeForRefresh);
    }

    /**
     * AccessToken발급
     *
     * @param user
     * @return String Accesstoken
     */
    public String generateAccessToken(User user){
        return generateJwtToken(user, exTimeForAcess);
    }


    /**
     * JWT Token을 생성
     *
     * @param user
     * @return JWT token
     */
    public String generateJwtToken(User user, String expiredTime){
        Date now = new Date();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setExpiration(new Date(now.getTime() + expiredTime))
                .signWith(SignatureAlgorithm.HS256,securityKey)
                .compact();

    }

    /**
     * JWT token header 생성
     *
     * @return Map <String, Object> header
     */
    private Map<String, Object> createHeader(){
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg","HS256");
        header.put("regDate", System.currentTimeMillis());
        return header;
    }

    /**
     * JWT token Claim 생성
     *
     * @param user
     * @return Map<String, Object> Claim </>
     */
    private Map<String, Object> createClaims(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("roles", user.getRole());
        return claims;
    }

    /**
     * Token에서 claim 추출
     *
     * @param token
     * @return
     */
    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(securityKey).parseClaimsJws(token).getBody();

    }

    /**
     * Token에서 유저 이름 추출
     *
     * @param token
     * @return String username
     */
    public String getUsernameFromToken(String token){
        return (String) getClaims(token).get("username");
    }

    /**
     * Token에서 RoleType 추출
     *
     * @param token
     * @return Set <RoleType> RoleType
     */
    public Set<RoleType> getRolFromToken(String token){
        return (Set<RoleType>) getClaims(token).get("roles");
    }



}
