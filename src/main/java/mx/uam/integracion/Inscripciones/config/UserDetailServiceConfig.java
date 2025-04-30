package mx.uam.integracion.Inscripciones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailServiceConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("user")
                .password(bCryptPasswordEncoder.encode("123"))
                .roles("ADMIN")
                .build());

        manager.createUser(User.withUsername("fer")
                .password(bCryptPasswordEncoder.encode("1711"))
                .roles("USER")
                .build());

        manager.createUser(User.withUsername("arturo")
                .password(bCryptPasswordEncoder.encode("9876"))
                .roles("USER")
                .build());

        return manager;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}