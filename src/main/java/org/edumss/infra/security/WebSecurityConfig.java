package org.edumss.infra.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    securityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic().and()
                .authorizeHttpRequests(authorization -> authorization
                        .antMatchers(HttpMethod.GET, "/product/**").authenticated()
                        .antMatchers(HttpMethod.POST, "/product").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "/product/**").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/product").hasRole("ADMIN")
                        .antMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    //Usuário em memoria
    //@Bean
    //public UserDetailsService userDetailsService() {
    //    UserDetails user = User
    //            .withUsername("Edu")
    //            .password(passwordEncoder().encode("123"))
    //            .roles("ADMIN")
    //            .build();
    //    return new InMemoryUserDetailsManager(user);
    //}


    //login apartir de uma conta cadastrada no Banco de dados
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
