package org.perscholas.security;

import org.perscholas.model.UserDetail;
import org.perscholas.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailService userDetailService;

    @Autowired
    GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/shop/**", "/register","/console/**").permitAll()
                // admin can only go to admin pages
                .antMatchers("/admin/**").hasRole("ADMIN")
                // and for any other request, user needs to be authenticated.
                .anyRequest()
                .authenticated()
                .and()
                // form login page is permitted to all
                .formLogin()
                .loginPage("/login")
                .permitAll()
                // if user is fail to login, it will check to login?error page
                .failureUrl("/login?error = true")
                // for login with email and password
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                // to authenticate login using google login
                .oauth2Login()
                .loginPage("/login")
                .successHandler(googleOAuth2SuccessHandler)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                // once the user's logins sessions is ended, invalidateHttpSession deletes the cookies from the browser
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")

                .and()
                .exceptionHandling()
                .and()
                .csrf()
                .disable();

    }
    // gives new BCryptPasswordEncoder object
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

     // creates custom user object- builds and passes user detail service object
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailService);
    }

    // ignores security check for antpatterns below
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**","/bookImages/**", "/css/**", "/js/**");
    }
}
