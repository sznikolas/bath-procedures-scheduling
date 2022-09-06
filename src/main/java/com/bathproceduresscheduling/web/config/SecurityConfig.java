package com.bathproceduresscheduling.web.config;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Bean
	public UserDetailsService userDetailService() {
		return super.userDetailsService();
	}
	
	@Autowired
	private UserDetailsService userService;
	
	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
		

	}
	
	@Override 
	protected void configure(HttpSecurity security) throws Exception {
		security
			.authorizeRequests()
					.antMatchers(
							"/",
							"/newuseraccountregistration/",
							"/js/**",
							"/css/**",
							"/img/**",
							"/webjars/**").permitAll()
					.antMatchers("/user/**").hasRole("USER")
					.antMatchers("/admin/**").hasRole("ADMIN")
					.antMatchers("/newuseraccountregistration").permitAll()   //minden usernek engedelyezve van h elerje a regisztraciot
					.antMatchers("/reg").permitAll()
					.antMatchers("/userprofile").permitAll()
					.antMatchers("/modositas").permitAll()
					.anyRequest().authenticated()
				.and()
				.formLogin()
					.loginPage("/login")
					.permitAll().defaultSuccessUrl("/registeredpatient")
				.and()
				.logout()
	                    .invalidateHttpSession(true)
	                    .clearAuthentication(true)
	                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                    .logoutSuccessUrl("/login?logout")
	                    .permitAll()
				.and()
				.exceptionHandling()
					.accessDeniedHandler(accessDeniedHandler);
					
					

				

	}
	
//	//Felhasználók bekonfigurálása
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)throws Exception {
//		auth
//			.inMemoryAuthentication()
//				.withUser("user")
//				.password("{noop}user")
//				.roles("USER")
//			.and()
//				.withUser("admin")
//				.password("{noop}admin")
//				.roles("ADMIN");
//	}

}
