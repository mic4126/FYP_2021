package hk.edu.cityu.cs.FYP.AIRegistry.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import hk.edu.cityu.cs.FYP.AIRegistry.filter.JWTAuthFilter;
import hk.edu.cityu.cs.FYP.AIRegistry.service.CustomAuthProvider;

@EnableWebSecurity()
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    CustomAuthProvider customAuthProvider;

    JWTAuthFilter jwtAuthFilter;

    @Autowired
    public void setJwtAuthFilter(JWTAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                .mvcMatchers("/login").permitAll()
                // .antMatchers("/admin/user/**").authenticated()
                // exclude reset password
                .mvcMatchers(HttpMethod.POST, "/admin/user/password",
                "/admin/user/password/{username}")
                .permitAll()
                .mvcMatchers("/admin/user/**").authenticated()

                // guard all user api
                // Attachment Controller related
                .mvcMatchers(HttpMethod.GET, "/project/{projectId}/attachment",
                        "/project/detail/{detailId}/attachment",
                        "/project/attachment/{attachmentId}")
                .permitAll()

                .mvcMatchers("/project/{projectId}/attachment",
                "/project/{projectId}/detail/{detailId}/attachment",
                "/project/attachment/{attachmentId}")
                .hasAnyRole("admin", "dev")

                // Detail Contoller
                .mvcMatchers(HttpMethod.GET, "/project/{projectId}/detail").permitAll()
                .mvcMatchers("/project/{projectId}/detail/**").hasAnyRole("admin",
                "dev")                
                // Project Controller
                .mvcMatchers("/project/{projectId}/user").authenticated()
                .mvcMatchers(HttpMethod.GET, "/project/{projectId}/tag").permitAll()
                .mvcMatchers(HttpMethod.GET, "/project/desc", "/project/name", "/project/{projectId}/name",
                        "/project/{projectId}/contact", "/project/contact")
                .permitAll()
                .mvcMatchers(HttpMethod.GET, "/project").permitAll()
                .mvcMatchers("/project/**").authenticated()
                // Search Controller
                .mvcMatchers("/search").permitAll()
                .mvcMatchers("/search/tag").permitAll()
                // Tag Controller
                .mvcMatchers(HttpMethod.GET,"/project/{projectId}/tag").permitAll()
                .mvcMatchers("/project/{projectId}/tag").authenticated()
                .anyRequest().denyAll()
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // dev work around
        // .antMatchers("/**").permitAll().and().csrf().disable()

        // .sessionManagement()
        // .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(customAuthProvider)
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}
