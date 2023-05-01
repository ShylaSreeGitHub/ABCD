package in.projects.loginandregistration.Config;

import in.projects.loginandregistration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserService userService;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(bCryptPasswordEncoder());
        return auth;
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
//        return configuration.getAuthenticationManager();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
                .requestMatchers("/registration**"
                ,"/js/**"
                ,"/css/**"
                ,"/img/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();
        return http.build();
    }
}

//public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
//        http.addFilter(new CustomFilter(authenticationManager));
//    }
//
//    public static MyCustomDsl customDsl() {
//        return new MyCustomDsl();
//    }
//}
