package com.SparkyTS.springboot.cruddemo.oauth2.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource securityDataSource;
//	 
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//        .withUser("admin").password("admin").roles("ADMIN").and()
//        .withUser("manager").password("manager").roles("MANAGER").and()
//        .withUser("employee").password("employee").roles("EMPLOYEE");
//		auth.jdbcAuthentication().dataSource(securityDataSource).passwordEncoder(passwordEncoder());
		auth.jdbcAuthentication().dataSource(securityDataSource).passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select username,password, status from user where username=?")
				.authoritiesByUsernameQuery(
						"SELECT u.username as username, a.role AS role FROM user u INNER JOIN user_authorities uaj ON uaj.user_id = u.id INNER JOIN authorities a ON a.id = uaj.authority_id where u.username=?");
	}

	@SuppressWarnings("deprecation")
	@Bean
	public NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	
//	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/oauth/token", "/api/users/**", "/api/userDetails").permitAll()
		.antMatchers(HttpMethod.GET, "/api/employees").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
		.antMatchers(HttpMethod.GET, "/api/employees/**").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.POST, "/api/employees").hasAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.POST, "/api/employees/**").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/employees").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/employees/**").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
		.anyRequest().authenticated();
		http.csrf().disable();
		http.exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
			Authentication auth 
	        = SecurityContextHolder.getContext().getAuthentication();
			System.out.println("test :::::"+auth.getName());
	      if (auth != null) {
	      	auth.getAuthorities().forEach(auth1 ->{
	      		System.out.println("auth1.getAuthority() ::::"+auth1.getAuthority());
	      	});
	      }
            AccessDeniedHandler defaultAccessDeniedHandler = new AccessDeniedHandlerImpl();
            defaultAccessDeniedHandler.handle(request, response, accessDeniedException);
        });
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
