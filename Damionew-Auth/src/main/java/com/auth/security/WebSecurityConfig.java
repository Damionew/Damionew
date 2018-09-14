package com.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.auth.feign.CommonFeign;

@Configuration
@EnableWebSecurity	/**注解开启Spring Security功能**/
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	CommonFeign commonFeign;
	
//	/**
//	 * 自定义用户登录验证逻辑
//	 */
//	@Bean
//	UserDetailsService customUserService() {
//		return new CustomUserService();
//	}
	/**
	 * security添加自定义登录验证
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService())
		.passwordEncoder(new MyPasswordEncoder())
		.and()
		.eraseCredentials(true);
	}
	/**
	 * 继承WebSecurityConfigurerAdapter并重写configure方法设置web安全细节
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		//解决SpringBoot不允许加载Iframe
		//"Refused to display 'http://......' in a frame because it set 'X-Frame-Options' to 'DENY'.
		httpSecurity.headers().frameOptions().disable();	
		httpSecurity.httpBasic();
		httpSecurity
			.csrf().disable()
			
			.authorizeRequests()
				//定义不需要被保护的URL
				.antMatchers("/assets/**","/css/**","/photoalbum/**","/bootstrap/**","/easyui/**",
						"/fluidgallery/**","/js/**","/timeline/**").permitAll()	
				//定义需要被保护的URL，即需要通过身份验证
				.anyRequest().authenticated()	
				.and()
			.formLogin()
				//定义当用户需要登录时，跳转的登录页面
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/index")
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				.invalidateHttpSession(true)
				.permitAll();
		
	}
	
	/**
	 * 内存中创建一个用户
	 */
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		@SuppressWarnings("deprecation")
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("sa")
				.password("1")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}
