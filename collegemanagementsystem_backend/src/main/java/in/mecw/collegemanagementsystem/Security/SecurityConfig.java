package in.mecw.collegemanagementsystem.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET","PUT","POST","DELETE","OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization","Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        http.authorizeHttpRequests(auth ->

                //security for register and login purposer
                auth.requestMatchers("/login","/error")
                        .permitAll()
                        .requestMatchers("/register")
                        .hasRole("ADMIN")

                        //security for student table operations
                        .requestMatchers(HttpMethod.GET,"/students/**")
                        .hasAnyRole("ADMIN","FACULTY")
                        .requestMatchers(HttpMethod.POST,"/students/**")
                        .hasAnyRole("ADMIN","FACULTY")
                        .requestMatchers(HttpMethod.PUT,"/students/**")
                        .hasAnyRole("ADMIN","FACULTY")
                        .requestMatchers(HttpMethod.DELETE,"/students/**")
                        .hasAnyRole("ADMIN","FACULTY")

                        //security for courses table operations
                        .requestMatchers(HttpMethod.GET,"/courses/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST,"/courses")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/courses/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/courses/**")
                        .hasRole("ADMIN")

                        //security for user table operations
                        .requestMatchers(HttpMethod.GET,"/users")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/users/password")
                        .authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/users/**")
                        .hasRole("ADMIN")

                        //security for faculties table
                        .requestMatchers(HttpMethod.GET,"/faculties/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST,"/faculties")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/faculties/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/faculties/**")
                        .hasRole("ADMIN")

                        //security for announcement table
                        .requestMatchers(HttpMethod.GET,"/announcements/**")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST,"/announcements/**")
                        .hasAnyRole("ADMIN","FACULTY")
                        .requestMatchers(HttpMethod.PUT,"/announcements/**")
                        .hasAnyRole("ADMIN","FACULTY")
                        .requestMatchers(HttpMethod.DELETE,"/announcements/**")
                        .hasAnyRole("ADMIN","FACULTY")

                        //security for topper table
                        .requestMatchers(HttpMethod.GET,"/toppers/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/toppers/**").hasAnyRole("ADMIN","FACULTY")
                        .requestMatchers(HttpMethod.PUT,"/toppers/**").hasAnyRole("ADMIN","FACULTY")
                        .requestMatchers(HttpMethod.DELETE,"/toppers/**").hasAnyRole("ADMIN","FACULTY")

                        //security for placed students table
                        .requestMatchers(HttpMethod.GET,"/placements/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/placements/**").hasAnyRole("ADMIN","FACULTY")
                        .requestMatchers(HttpMethod.PUT,"/placements/**").hasAnyRole("ADMIN","FACULTY")
                        .requestMatchers(HttpMethod.DELETE,"/placements/**").hasAnyRole("ADMIN","FACULTY")

                        //security for complaint table
                        .requestMatchers(HttpMethod.GET,"/complaints/mine").authenticated()
                        .requestMatchers(HttpMethod.POST,"/complaints/**").authenticated()
                        .requestMatchers(HttpMethod.GET,"/complaints/**").hasAnyRole("ADMIN","FACULTY")
                        .requestMatchers(HttpMethod.PUT,"/complaints/**").hasAnyRole("ADMIN","FACULTY")
                        .requestMatchers(HttpMethod.DELETE,"/complaints/**").hasRole("ADMIN")

                        //security for studymaterials
                        .requestMatchers(HttpMethod.GET,"/materials/**").authenticated()
                        .requestMatchers(HttpMethod.POST,"/materials/**").hasAnyRole("ADMIN","FACULTY")
                        .requestMatchers(HttpMethod.DELETE,"/materials/**").hasAnyRole("ADMIN","FACULTY")

                        .anyRequest()
                        .authenticated()
        );
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}