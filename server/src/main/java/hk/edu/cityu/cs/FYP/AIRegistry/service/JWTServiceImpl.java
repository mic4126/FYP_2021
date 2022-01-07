package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import hk.edu.cityu.cs.FYP.AIRegistry.dao.UserDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.LoginReq;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTServiceImpl {

    
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDao userDao;

    @Value("${jwt.key}")
    private String KEY;

    

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String genToken(LoginReq loginReq) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginReq.getUsername(),
                loginReq.getPassword());
        authentication = authenticationManager.authenticate(authentication);

        UserInfo userInfo = (UserInfo)authentication.getPrincipal();

        Calendar expireTime = Calendar.getInstance();
        expireTime.add(Calendar.HOUR, 8);

        Claims claims = Jwts.claims();
        claims.put("userType", userInfo.getUserType());
        claims.put("username", userInfo.getUsername());
        claims.put("projectIds", userInfo.getProjectIds());
        claims.setExpiration(expireTime.getTime());
        claims.setNotBefore(Calendar.getInstance().getTime());
        claims.setIssuer("AIRegistry");

        Key hmacKey = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder().setClaims(claims).signWith(hmacKey).compact();
    }

    public Map<String, Object> parseToken(String token) {
        Key hmacKey = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));

        var parser = Jwts.parserBuilder().setSigningKey(hmacKey).build();

        Claims claims = parser.parseClaimsJws(token).getBody();

        return claims.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

}
