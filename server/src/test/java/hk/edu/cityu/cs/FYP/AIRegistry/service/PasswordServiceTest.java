package hk.edu.cityu.cs.FYP.AIRegistry.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import hk.edu.cityu.cs.FYP.AIRegistry.BaseTest;

public class PasswordServiceTest extends BaseTest {
    
    @Autowired
    private PasswordService passwordService;

    @Test
    void generatePasswordTest(){
        var password = passwordService.generatePassword();
        assertThat(password).isNotNull().isNotEmpty().isNotEqualTo("");
    }

    @Test
    void generateSaltTest(){
        var password = passwordService.generateSalt();
        assertThat(password).isNotNull().isNotEmpty().isNotEqualTo("");
    }

    @Test
    void checkPasswordTest(){
        String hash = "$2a$10$iSlfLAzERJHTVlMS72cPh.lhtJFHhDFwPasIC6Ee/kTbQjbZGsJcS";
        String password = "test";
        String salt = "";

        assertThat(passwordService.checkPassword(password, salt, hash)).isTrue();
        

    }

    @Test
    void checkPasswordTest_SaltNull(){
        String hash = "$2a$10$iSlfLAzERJHTVlMS72cPh.lhtJFHhDFwPasIC6Ee/kTbQjbZGsJcS";
        String password = "test";
        String salt = null;

        assertThat(passwordService.checkPassword(password, salt, hash)).isTrue();
        
    }

    @Test
    void hashedPasswordTest(){
        String password = "test";
        String salt = "";

        var hashedpassword = passwordService.hashPassword(password, salt);

        assertThat(passwordService.checkPassword(password, salt, hashedpassword)).isTrue();

    }
}
