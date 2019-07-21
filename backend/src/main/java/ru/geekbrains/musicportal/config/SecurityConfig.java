package ru.geekbrains.musicportal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.geekbrains.musicportal.service.user.UserServiceImpl;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    private MusicportalAuthenticationSuccessHandler authenticationSuccessHandler;

//    @Lazy
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

//    @Lazy
//    @Autowired
//    public void setAuthenticationSuccessHandler(MusicportalAuthenticationSuccessHandler authenticationSuccessHandler) {
//        this.authenticationSuccessHandler = authenticationSuccessHandler;
//    }

    /**
     * Как будет проходить аутентификация
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                    .antMatchers("/**").permitAll();
//                    .anyRequest().authenticated()
//                .and()
//                    .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .and()
//                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http
//                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    //    /**
//     * Настройка защиты приложения
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                    .authorizeRequests()
////                    .anyRequest().authenticated()
//                    .antMatchers("/**").permitAll();
//    //                .antMatchers("/register/**").permitAll()
//    //                .antMatchers("/products/**").hasRole("ADMIN")
//    //                .antMatchers("/shop/order/**").authenticated()
//    //                .antMatchers("/profile/**").authenticated()
////                .and()
////                    .formLogin()
////                    .loginPage("/login")
////                    .loginProcessingUrl("/authenticateTheUser")
////                    .successHandler(authenticationSuccessHandler)
////                    //               .failureHandler()
////                    .permitAll()
////                .and()
////                    .logout()
////                    .logoutSuccessUrl("/login")
////                    .permitAll();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public MusicportalAuthenticationProvider authenticationProvider() {
//        MusicportalAuthenticationProvider auth = new MusicportalAuthenticationProvider();
//        auth.setUserService(userService);
//        return auth;
//    }
}
