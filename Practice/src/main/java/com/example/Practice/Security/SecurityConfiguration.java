//package com.example.Practice.Security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.web.SecurityFilterChain;
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration  {
// 
//	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
//	{
//		httpSecurity.authorizeHttpRequests
//		(authorizeRequests->authorizeRequests.requestMatchers("/user/**").authenticated())
//		.httpBasic(Customizer.withDefaults());
//		
//		return httpSecurity.build();
//	}
//}
