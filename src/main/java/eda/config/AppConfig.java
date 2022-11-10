package eda.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eda.report.report.ReportInitiator;

@EnableWebMvc
@Configuration
@ComponentScan("eda")// Kun chai package vitra component haru search garni
public class AppConfig {
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// to store and verify encoded password
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DataSource dataSource() {
		//Database configuration
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/eda");
		ds.setUsername("root");
		ds.setPassword("");
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		// To run jdbc query
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}
	
	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager() {
		// Not used currently
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource());
		return jdbcUserDetailsManager;
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
	    commonsMultipartResolver.setMaxUploadSize(1073741824);//1GB
	    commonsMultipartResolver.setDefaultEncoding("UTF-8");
	    return commonsMultipartResolver;
	}
	
	@Bean
	public ReportInitiator reportInitiator() {
		return new ReportInitiator();
	}
	
	@Bean
	public Gson gson() {
		return new GsonBuilder().setPrettyPrinting().create();
	}
	
	/**
	 * Note: Create AccountConfiguration.java manually which is not shown for security purpose
	 * @return JavaMailSender
	 */
	@Bean
	public JavaMailSenderImpl javaMailSenderImpl() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setUsername(AccountConfiguration.getEmail());
		javaMailSender.setPassword(AccountConfiguration.getPassword());
		javaMailSender.setPort(587);
		Properties mailProps = new Properties();
		mailProps.put("mail.smtp.starttls.enable", "true");
		mailProps.put("mail.smtp.starttls.required", "true");
		mailProps.put("mail.smtp.auth", "true");
		mailProps.put("mail.smtp.ssl.trust","smtp.gmail.com");
		javaMailSender.setJavaMailProperties(mailProps);
		return javaMailSender;
	}
}
