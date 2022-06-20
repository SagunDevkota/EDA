package eda.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
//to create the spring security filter chain
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("sagun")
		.password("{bcrypt}$2a$12$UL.HyGUxf4aSTD6nyf4c2uMBmn72z1zWo6w01OgCK7xJEaSimpoe2")
		.roles("admin");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/home").authenticated()//.hasRole() for role based access
		.antMatchers("/user/*").authenticated()
		.and()
			.formLogin()
			.and()
			.httpBasic();
	}
}
