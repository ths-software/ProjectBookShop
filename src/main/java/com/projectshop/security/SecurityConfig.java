package com.projectshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception
	{
		return auth.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception
	{
		httpSec
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/css/**", "/webjars/**", "/h2-console/**").permitAll()
					.requestMatchers("/", "/public/**", "/auth/**", "/files/**", "/favicon.ico").permitAll()
					.requestMatchers("/admin/**").hasAnyRole("OWNER","ADMIN")
					.anyRequest().authenticated())

			.formLogin(login-> login
					.loginPage("/auth/login")
					.defaultSuccessUrl("/public/", true)
					.loginProcessingUrl("/auth/login-post")
					.permitAll())
			.logout(logout -> logout
					.logoutUrl("/auth/logout")
					.logoutSuccessUrl("/public/"))
			/*.csrf(csrf -> csrf.disable())*/
			/*.headers(headers -> headers
		            .frameOptions(frame -> frame.disable()))*/ ;
		
		return httpSec.build();
	}
	
	
	/*
	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsManager()
	{
		UserDetails u = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("admin")
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(u);
	}
	*/

}
