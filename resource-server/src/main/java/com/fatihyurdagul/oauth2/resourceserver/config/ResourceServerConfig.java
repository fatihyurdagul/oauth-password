package com.fatihyurdagul.oauth2.resourceserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@Configuration
public class ResourceServerConfig {
  // Resource Server için herhangi bir konfigürasyona şuanda gerek yok. Sadece anotasyonu koymamız yeterli olacaktır.
}
