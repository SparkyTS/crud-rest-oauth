package com.SparkyTS.springboot.cruddemo.oauth2.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {	
	
	@Autowired
	private DataSource securityDataSource;
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//        .withUser("admin").password("admin").roles("ADMIN").and()
//        .withUser("manager").password("manager").roles("MANAGER").and()
//        .withUser("employee").password("employee").roles("EMPLOYEE");
//		auth.jdbcAuthentication().dataSource(securityDataSource).passwordEncoder(passwordEncoder());
		auth.jdbcAuthentication().dataSource(securityDataSource)
		.usersByUsernameQuery(
			"select username,password, status from user where username=?")
		.authoritiesByUsernameQuery(
			"select username, role from authorities where username=?");
    }
	
	@SuppressWarnings("deprecation")
	@Bean
	public NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
//	
	
//	@Bean
//	public BCryptPasswordEncoder BCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http.requestMatchers().and()
		.authorizeRequests() 
		.antMatchers("/api/users/**").permitAll()
		.antMatchers("/oauth/token").permitAll()
		.antMatchers(HttpMethod.GET, "/api/employees").hasAnyRole("EMPLOYEE","MANAGER","ADMIN")
		.antMatchers(HttpMethod.GET, "/api/employees/**").hasAnyRole("EMPLOYEE","MANAGER","ADMIN")
		.antMatchers(HttpMethod.POST, "/api/employees").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.POST, "/api/employees/**").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/employees").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/employees/**").hasAnyRole("MANAGER", "ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN");
		http.csrf().disable();
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
