package com.huna.basic.provider;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;


// JWT : 
// - Json Web Token, RFC 7519 표준에 정의된 json 형식의 문자열을 포함하고 있는 토큰 
// - 인증 및 인가에 사용
// - 암호화가 되어 있어 클라이언트와 서버 간의 안전한 데이터 교환을 할 수 있음
// - 헤더, 페이로드, 서명으로 구성됨
// - 헤더 : 토큰의 유형 (일반적으로 jwt)과 암호화 알고리즘이 포함되어 있음
// - 페이로드 : 클라이언트 혹은 서버가 상대방에게 전달할 데이터가 포함되어 있음 (작성자, 접근주체의 정보, 작성시간, 만료시간 등)
// - 서명 : 헤더와 페이로드를 합쳐서 인코딩하고 지정한 비밀키로 암호화한 데이터

// 제어의 역전(스프링에게 JwtProvider에 대한 권한을 넘겨줌) @Component
@Component
public class JwtProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;
    
    // JWT 생성
    public String create(String principle) {
        // 만료시간
        Date expiredDate = Date.from(Instant.now().plus(4, ChronoUnit.HOURS));
        // 비밀키 생성 -> 보이면 안됌(환경변수만 저장하는 서버에 저장, )
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        // JWT 생성
        String jwt = Jwts.builder()
            // 서명 (서명에 사용할 비밀키, 서명에 사용할 암호화 알고리즘)
            .signWith(key, SignatureAlgorithm.HS256)
            // 페이로드--------
            // "sub" 작성자
            .setSubject(principle)
            // "iat" 생성시간
            .setIssuedAt(new Date())
            // "exp" 만료시간
            .setExpiration(expiredDate)
            // 위의 내용을 압축(인코딩)
            .compact(); 

        

        return jwt;
    }

    public String validation(String jwt) {

        // jwt 검증 결과로 나타나는 페이로드가 저장될 변수
        Claims claims = null;
        // 비밀키 생성
        // Key key = Keys.hmacShaKeyFor("qwerasdfzxcvqwerasdfzxcvqwerasdfzxcvqwerasdfzxcv".getBytes(StandardCharsets.UTF_8));
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        // 예외사항
        try {
            // 비밀키로 jwt 복호화 작업
            claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody(); // 성공하면 실제 데이터 가져온다

        } catch(Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return claims.getSubject();

    }

}