package com.fatihyurdagul.oauth2.authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// WebSecurityConfigurerAdapter kullanmamızın sebebi Authentication Manager'i context'a gönderebilmek.
@Configuration
public class UserManagementConfig extends WebSecurityConfigurerAdapter {
  
  @Bean
  public UserDetailsService customUserDetailsService() {
    InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
    // Sisteme giriş yapacak kullanıcıyı oluşturuyoruz.
    UserDetails user = User.withUsername("fatih") // Resource server'a ulaşmak isteyen kişinin Authorization Server daki hesabı.
                      .password("12345")
                      .authorities("read").build();
    // Kullanıcıyı userDetails Service'imize ekliyoruz.
    userDetailsManager.createUser(user);
    return userDetailsManager;
  }
  
  //  Spring zorunlu olarak kullanıcıların parolasını encrypt etmek için password encoder'a ihtiyaç duyar.
  // Biz şimdilik encrypt etmesini istemiyoruz.
  @Bean
  public PasswordEncoder  passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
  
  
  //  Authentication Manager'i diğer sınıflarda çağırabilmek için context' gönderiyoruz.
  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
