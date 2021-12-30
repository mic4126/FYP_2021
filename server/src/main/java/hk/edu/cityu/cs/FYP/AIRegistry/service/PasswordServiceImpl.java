package hk.edu.cityu.cs.FYP.AIRegistry.service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String generatePassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            @Override
            public String getCharacters() {
                return "~!@#$%^&*()_+=<>/?,.;':\"\\|[]{}";
            }

            @Override
            public String getErrorCode() {
                
                return "Special_Char_Error_Code";
            }
        };
        CharacterRule specialCharRule = new CharacterRule(specialChars);
        specialCharRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(10, specialCharRule, lowerCaseRule,
                upperCaseRule, digitRule);

        return password;
    }

    public String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String token = encoder.encodeToString(bytes);
        return token;
    }

    public String hashPassword(String password, String salt) {
        return encoder.encode(password + salt);
    }

    @Override
    public boolean checkPassword(String userTypePassword, String salt, String hashedpassword) {
        return encoder.matches(userTypePassword + salt, hashedpassword);
    }

}
