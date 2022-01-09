package hk.edu.cityu.cs.FYP.AIRegistry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import hk.edu.cityu.cs.FYP.AIRegistry.dao.UserDao;
import hk.edu.cityu.cs.FYP.AIRegistry.model.UserInfo;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (null == authentication.getPrincipal() || null == authentication.getCredentials()) {
            throw new BadCredentialsException("Username or password invaild");
        }
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        UserInfo userInfo;
        try {
            userInfo = (UserInfo) userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("Username or password invaild");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(password, userInfo.getHashedPassword())) {
            Authentication auth = new UsernamePasswordAuthenticationToken((UserDetails) userInfo,
                    userInfo.getHashedPassword(), userInfo.getAuthorities());
            if (userInfo.getNewHashedPassword() != null) {
                userService.useOldPasswordLogin(username);
            }
            return auth;
        } else if (passwordEncoder.matches(password, userInfo.getNewHashedPassword())) {
            Authentication auth = new UsernamePasswordAuthenticationToken((UserDetails) userInfo,
                    userInfo.getNewHashedPassword(), userInfo.getAuthorities());
            userService.useNewPasswordLogin(username);
            return auth;
        }
        throw new BadCredentialsException("Username or password invaild");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
