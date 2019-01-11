package com.nelioalves.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/*
 * configuracao de segurança
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	private Environment env;
	
	// QUAIS CAMINHOS ESTA LIBERADOS
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_GET = { //APENAS LEITURA
			"/h2-console/**",
			"/produtos/**",
			"/categorias/**"
	};
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {//pega todos os profiles ativo se eu tiver no test libera todos os acessos
			http.headers().frameOptions().disable();
		}
		http.cors().and().csrf().disable();
		http.authorizeRequests() 
		.antMatchers(HttpMethod.GET,PUBLIC_MATCHERS_GET).permitAll() //apenas os metodos get para essa categorias
		.antMatchers(PUBLIC_MATCHERS).permitAll()//PERMITE TODOS OS CAMINHOS QUE ESTA NESSE VETOR 
		.anyRequest().authenticated();// O RESTO PRECISA AUTENTICAR
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	  CorsConfigurationSource corsConfigurationSource() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues()); ///PERMITINDO ACESSO A TODOS OS ENDPOINTS
	    return source;
	  }
}
