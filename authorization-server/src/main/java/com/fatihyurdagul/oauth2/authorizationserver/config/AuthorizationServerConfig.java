package com.fatihyurdagul.oauth2.authorizationserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
 
  @Autowired
  private AuthenticationManager authenticationManager;
  
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.passwordEncoder(NoOpPasswordEncoder.getInstance())
            .checkTokenAccess("permitAll()");
  }
  
  //  burada Authorization server'a client'in kayıt olduğunu düşünüyoruz.
  //  InMemory olarak client1'i authorization server'a register ettik.
  //  Grant Type olarak password tipinde bir client olduğunu belirttik.
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    // Resource Server'a erişebilmek için bu client'in önce Authorization Server'dan token alması gerekecek.
    clients.inMemory()
           .withClient("client1") // client kullanıcı adı olarak düşünülebilir.
           .secret("secret1") // client parolası olarak düşünülebilir.
           .scopes("read")
           .accessTokenValiditySeconds(5000) // token geçerlilik süresi
           .authorizedGrantTypes("password")
    .and()
          .withClient("resourceserver")// Resource server için bir client hesabı oluşturuyoruz.
          .secret("12345"); // Tokeni kontrol ederken bu hesabı kullanacak.
  }
  
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager);
  }
}
