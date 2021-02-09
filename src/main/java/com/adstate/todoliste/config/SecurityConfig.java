package com.adstate.todoliste.config;

import com.adstate.todoliste.filter.JwtFilter;
import com.adstate.todoliste.services.users.MyUserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

@Autowired
  private MyUserDetailsServices userDetailsService;

  @Autowired
  private JwtFilter jwtFilter;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  @Bean
  public PasswordEncoder passwordEncoder()
  {
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   *
   * @param http
   * @throws Exception
   */

  // .antMatchers("/jwt/authenticate", "/user/hello").permitAll()
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Disable CSRF
    http.csrf().disable()
            .authorizeRequests().antMatchers("/todoliste/create").hasRole("ADMIN")
            .antMatchers( "/h2/**").permitAll()
            // any authenticated user can perform all other operations
            .antMatchers("/todoliste/**").hasAnyRole("ADMIN", "USER")
            .antMatchers("/jwt/authenticate").permitAll()
            .antMatchers("/user/**").hasAnyRole("ADMIN")
            .anyRequest().authenticated()
            .and().exceptionHandling()
            .and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.headers().frameOptions().disable();
    // Add a filter to validate the tokens with every request
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web
            .ignoring()
            .antMatchers("/h2-console/**");
  }
}


