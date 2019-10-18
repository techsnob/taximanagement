package com.techsnob.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.techsnob.service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private UserService userService;
    
    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery)
        .authoritiesByUsernameQuery(rolesQuery).dataSource(dataSource).passwordEncoder(passwordEncoder());
	}
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
	protected void configure(final HttpSecurity http) throws Exception {
	   http.csrf().disable().authorizeRequests()
	  .antMatchers("/resources/**").permitAll()
	  .antMatchers("/registration*").permitAll()
	  .antMatchers("/h2-console*").permitAll()
	  .antMatchers("/admin/**").hasRole("ADMIN")
	  .antMatchers("/login*").permitAll()
	  .anyRequest().authenticated()
	  .and()
	  .formLogin()
	  .loginPage("/login.html")
	  .defaultSuccessUrl("/index.html",false)
	  //.failureUrl("/login.html?error=true")
	  //.failureHandler(authenticationFailureHandler()) 
	  .and()
	  .logout()
	  .logoutUrl("/logout") .deleteCookies("JSESSIONID");
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
    
}