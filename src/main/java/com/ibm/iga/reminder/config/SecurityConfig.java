package com.ibm.iga.reminder.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import com.ibm.iga.reminder.filter.CsrfHeaderFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//auth.authenticationProvider(loginAuthenticationProvider);
		//auth.userDetailsService(userDetailsService);
		//auth.eraseCredentials(false);
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
				.usersByUsernameQuery("select username,password, enabled from users where username=?")
				.authoritiesByUsernameQuery("select username, authority from authorities where username=?");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
					.antMatchers("/resources/**").permitAll()
					.antMatchers("/").permitAll()
					.antMatchers("/api/register/**").permitAll()
					.antMatchers("/register/**").permitAll()
					.antMatchers("/admin/**").hasRole("ADMIN")
					.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
					.anyRequest().authenticated()
				.and()
					.formLogin()
					.loginPage("/login")
					.permitAll()
				.and()
					.logout()
						.permitAll()
						//.invalidateHttpSession(true)
				.and() 
					.httpBasic()
				.and()
					.csrf().csrfTokenRepository(csrfTokenRepository())
				.and()
					.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
				//.and()
				//	.rememberMe();
		super.configure(http);
	}
	 
	
	private CsrfTokenRepository csrfTokenRepository() {
		  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		  repository.setHeaderName("X-XSRF-TOKEN");
		  return repository;
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}
	
	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler 
                savedRequestAwareAuthenticationSuccessHandler() {
               SavedRequestAwareAuthenticationSuccessHandler auth 
                    = new SavedRequestAwareAuthenticationSuccessHandler();
		auth.setTargetUrlParameter("targetUrl");
		return auth;
	}	
}
