package com.doctor.spa.configuration;

import com.doctor.spa.util.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
@ComponentScan({ "com.doctor.spa" })
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
		.antMatchers("/resources/css/**", "/resources/javascript/**", "/resources/image/**", "/resources/fonts/**", "/admin/login", "/home", "/", "/service", "/news", "/contact", "/service/**", "/billing", "/billing/**", "/bucket", "/bucket/**", "/news/**", "/messenger/**", "/booking", "/booking/**").permitAll()
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
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(new AuthenticationProvider() {
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				String username = authentication.getName();
				String password = PasswordEncryptor.generateSecurePassword(
						authentication.getCredentials().toString(),
						PasswordEncryptor.getSaltvalue(30));
				return new UsernamePasswordAuthenticationToken(
						username, password, new ArrayList<>());
			}

			@Override
			public boolean supports(Class<?> aClass) {
				return false;
			}
		});
	}
}

