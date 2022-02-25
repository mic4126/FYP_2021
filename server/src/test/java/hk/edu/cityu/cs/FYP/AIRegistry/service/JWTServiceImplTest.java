package hk.edu.cityu.cs.FYP.AIRegistry.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;

import hk.edu.cityu.cs.FYP.AIRegistry.BaseTest;
import hk.edu.cityu.cs.FYP.AIRegistry.model.LoginReq;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JWTServiceImplTest extends BaseTest {

    @Autowired
    private JWTServiceImpl jwtServiceImpl;

    @MockBean
    private PasswordService passwordService;

    @Value("${jwt.key}")
    private String KEY;

    @Test
    void genTokenTest() {

        when(passwordService.checkPassword(any(), any(), any())).thenReturn(true);

        var loginReq = new LoginReq();

        loginReq.setUsername("dev");
        loginReq.setPassword("123");

        var token = jwtServiceImpl.genToken(loginReq);

        Key hmacKey = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));

        var parser = Jwts.parserBuilder().setSigningKey(hmacKey).build();

        var claims = parser.parseClaimsJws(token).getBody();

        assertThat(claims.getIssuer()).isEqualTo("AIRegistry");
        assertThat(claims.get("username")).isEqualTo("dev");

    }

    @Test
    void parseTokenTest() {

        when(passwordService.checkPassword(any(), any(), any())).thenReturn(true);

        var loginReq = new LoginReq();

        loginReq.setUsername("dev");
        loginReq.setPassword("123");

        var token = jwtServiceImpl.genToken(loginReq);

        var claims = jwtServiceImpl.parseToken(token);

        assertThat(claims.get("username")).isEqualTo("dev");

    }

}
