package com.theam.CRMService.crmrestapi.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import com.allanditzel.springframework.security.web.csrf.CsrfTokenResponseHeaderBindingFilter;
import com.theam.CRMService.crmrestapi.security.authentication.CRMUserDetailsService;
import com.theam.CRMService.crmrestapi.security.authentication.handlers.CRMAccessDeniedHandler;
import com.theam.CRMService.crmrestapi.security.authentication.handlers.CRMAuthenticationEntryPoint;
import com.theam.CRMService.crmrestapi.security.authentication.handlers.CRMAuthenticationFailHandler;
import com.theam.CRMService.crmrestapi.security.authentication.handlers.CRMAuthenticationSuccessHandler;
import com.theam.CRMService.crmrestapi.security.authentication.handlers.CRMLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CRMSecurityConfig extends WebSecurityConfigurerAdapter {

	
		@Bean
		public PasswordEncoder passwordEncoder() {
		    return new BCryptPasswordEncoder();
		}
	
	
	  @Autowired
	  private CRMAuthenticationSuccessHandler loginSuccessfulHandler;
	  @Autowired
	  private CRMAuthenticationFailHandler loginFailureHandler;
	  @Autowired
	  private CRMLogoutSuccessHandler logoutSuccessfulHandler;
	  @Autowired
	  private CRMAccessDeniedHandler customAccessDeniedHandler;
	  @Autowired
	  private CRMAuthenticationEntryPoint customAuthenticationEntryPoint;
	
		
	  @Autowired
	  private CRMUserDetailsService userDetailsService;
	  @Autowired
	  private DataSource jdbcDatasource;
	  @Override
	  @Bean
	  public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	  }
	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	      auth.userDetailsService(userDetailsService)
	        .and().jdbcAuthentication().dataSource(jdbcDatasource);
	  }
	  @Override
	  protected void configure(HttpSecurity http) throws Exception {
		  
		  //TODO: Will this API be served in browsers?
		  http.csrf().disable();
		  
		  //Login
		  http.formLogin().loginProcessingUrl("/auth/login");
		  http.formLogin().successHandler(loginSuccessfulHandler).failureHandler(loginFailureHandler);
		  
		  //Logout
		  http.logout().deleteCookies("JSESSIONID").logoutUrl("/auth/logout").logoutSuccessHandler(logoutSuccessfulHandler);
		  
		  //Authorisation
		  http.authorizeRequests().antMatchers("/api/").authenticated();
		  http.authorizeRequests().antMatchers("/api/users").hasRole("ADMIN").anyRequest().authenticated();
		  http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler).authenticationEntryPoint(customAuthenticationEntryPoint);
		  
		  http.anonymous().disable();
		  


	      http.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);
	  }
}
