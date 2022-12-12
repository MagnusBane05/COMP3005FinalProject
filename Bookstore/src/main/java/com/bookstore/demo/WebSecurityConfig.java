package com.bookstore.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bookstore.demo.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/webjars/**").permitAll()
			.antMatchers("/signup").permitAll()
			.antMatchers("/register").permitAll()
			.antMatchers("/register_complete").permitAll()
			.antMatchers("/").hasAnyAuthority("CUSTOMER", "ADMIN")
			.antMatchers("/books").hasAnyAuthority("CUSTOMER", "ADMIN")
			.antMatchers("/books/purchase").hasAnyAuthority("ADMIN")
			.antMatchers("/orders").hasAnyAuthority("CUSTOMER")
			.antMatchers("/reports").hasAnyAuthority("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.permitAll()
				.loginPage("/login")
				.usernameParameter("username")
				.failureUrl("/login_error")
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/403");
	}
}
