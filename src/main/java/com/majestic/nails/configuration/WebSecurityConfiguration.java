package com.majestic.nails.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan({ "com.majestic.nails" })
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAfter(new AjaxTimeoutRedirectFilter(), ExceptionTranslationFilter.class);
		
		http.csrf().disable().headers()
        .frameOptions().disable()
        .and()
		.authorizeRequests()
		.antMatchers("/resources/css/**", "/resources/javascript/**", "/resources/image/**", "/resources/fonts/**", "/admin/login", "/home", "/", "/service", "/post", "/contact", "/pricelist", "/service/**", "/billing", "/billing/**", "/bucket", "/bucket/**", "/post/**", "/messenger/**", "/booking", "/booking/**", "/api/**").permitAll()
		.antMatchers("/admin/**", "/admin").hasAnyAuthority("admin", "Admin", "ADMIN").anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/admin/login").failureUrl("/admin/login?error")
		.defaultSuccessUrl("/admin/main", true)
		.usernameParameter("username").passwordParameter("password")
		.permitAll()
		.and()
		.logout().logoutSuccessUrl("/admin/login")
		.deleteCookies("JSESSIONID")
		.permitAll();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}
}

