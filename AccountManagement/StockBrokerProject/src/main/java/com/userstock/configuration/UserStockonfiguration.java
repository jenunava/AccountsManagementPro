package com.accounts.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * User Account Configuration Class
 * @author jnavamshan
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.accounts")
public class UserAccountConfiguration {
	

}