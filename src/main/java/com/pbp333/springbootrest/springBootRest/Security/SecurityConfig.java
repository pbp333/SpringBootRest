package com.pbp333.springbootrest.springBootRest.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(AuthenticationManagerBuilder auth){

        try {
            auth.inMemoryAuthentication().withUser("user1").password("secret1").roles("USER").and().withUser("admin1").password("secret1").roles("USER", "ADMIN");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void configure(HttpSecurity httpSecurity){

        try {
            httpSecurity.httpBasic().and().authorizeRequests().
                    antMatchers("/surveys/**").hasRole("USER").
                    antMatchers("/users/**").hasRole("USER").
                    antMatchers("/**").hasRole("ADMIN").
                    and().csrf().disable().headers().frameOptions().disable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
