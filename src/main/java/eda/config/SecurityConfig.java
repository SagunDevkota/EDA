package eda.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
//to create the spring security filter chain
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		authorizeRequests().
		anyRequest().
		authenticated().
		and().
		formLogin().
		and().
		httpBasic();
	}
}
