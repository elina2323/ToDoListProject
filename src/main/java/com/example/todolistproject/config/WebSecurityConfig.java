package com.example.todolistproject.config;

import com.example.todolistproject.security.jwt.JwtConfigurer;
import com.example.todolistproject.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements ApplicationContextAware,WebMvcConfigurer {

    @Autowired
    private  JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "**/api/v1/admin/**";
    private static final String LOGIN_ENDPOINT = "**/api/v1/auth/**";
    private static final String TASK_ENDPOINT = "**/api/v1/tasks/**";
       private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html/",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };

//     @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedMethods("*");
//    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
     @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder.encode("admin2323"))
                .roles("ADMIN", "SWAGGER");
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authProvider;
    }
    
//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//                 .httpBasic().disable()
//                 .csrf().and().cors().disable()
//                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                 .and()
//                 .authorizeRequests()
//                 .antMatchers(AUTH_WHITELIST).permitAll()
//                 .antMatchers(HttpMethod.POST, LOGIN_ENDPOINT).permitAll()
//                 .antMatchers(ADMIN_ENDPOINT).permitAll()
//                 .anyRequest().authenticated()
//                 .and()
//                 .apply(new JwtConfigurer(jwtTokenProvider));
//     }
//       @Override
//     public void configure(WebSecurity web) throws Exception {
//         web.ignoring().antMatchers("/v2/api-docs",
//                 "/configuration/ui",
//                 "/swagger-resources/**",
//                 "/configuration/**",
//                 "/swagger-ui.html",
//                 "/webjars/**,/api/**");

//     }
     @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                sessionManagement()
                .and().authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .antMatchers(TASK_ENDPOINT).permitAll()
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN").anyRequest().authenticated().and().formLogin();

    }

   
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.cors().and().csrf().disable();
//    }
//     @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("*"));
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

//    @Bean(name="configure")
//    @Conditional(DevConditional.class)
//    public SecurityWebFilterChain configureDev(ServerHttpSecurity http) throws Exception {
//        return http
//                .csrf().disable()
//                .authorizeExchange()
//                .pathMatchers("/v2/api-docs").permitAll()
//                .pathMatchers("/configuration/ui").permitAll()
//                .pathMatchers("/swagger-resources/**").permitAll()
//                .pathMatchers("/configuration/security").permitAll()
//                .pathMatchers("/swagger-ui.html").permitAll()
//                .pathMatchers("/swagger-ui/*").permitAll()
//                .pathMatchers("/webjars/**").permitAll()
//                .pathMatchers("/v2/**").permitAll()
//                .and().cors()
//                .and().oauth2ResourceServer()
//                .jwt().and().and().build();
//    }
}
