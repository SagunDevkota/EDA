package eda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

import eda.filter.LoginFilter;
import eda.service.UserDetailServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
//to create the spring security filter chain	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	UserDetailServiceImpl userDetailServiceImpl; 
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		jdbcTemplate.execute("Select id FROM clients WHERE email = ?");
//		auth.jdbcAuthentication()
//		.usersByUsernameQuery("SELECT email,password,id FROM clients WHERE email=?")
//		.authoritiesByUsernameQuery("SELECT email,Role from clients where email=?")
//		.dataSource(dataSource)
//		.passwordEncoder(passwordEncoder);
		
		// to authenticate user based on data retrieved from userDetailServiceImpl.
		auth.userDetailsService(userDetailServiceImpl).passwordEncoder(passwordEncoder);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//provide login/logout page and access control
		http.addFilterBefore(new LoginFilter(), DefaultLoginPageGeneratingFilter.class);
		http.
		authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/process-signup").permitAll()
		.antMatchers("/dashboard").authenticated()//.hasRole() for role based access
		.antMatchers("/upload").authenticated()
		.antMatchers("/report").authenticated()
		.antMatchers("/reports").authenticated()
		.antMatchers("/uploadFile").authenticated()
		.antMatchers("/user/*").authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/dashboard",true)
			.failureUrl("/loginError")//.loginProcessingUrl(null)
			.and()
			.httpBasic()
			.and()
			.logout()
			.logoutSuccessUrl("/login?logout=true")
			.invalidateHttpSession(true);
	}
}
