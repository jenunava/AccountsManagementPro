package com.userstock.configuration;

 
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * User Stock Configuration Class
 * @author jnavamshan
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.userstock")
public class UserStockonfiguration {
	

}