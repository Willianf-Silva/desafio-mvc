package com.gft.desafiomvc;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class DesafioMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioMvcApplication.class, args);
	}

	/*
	 * Método responsável por fixar a região que será tratato as datas, numero, etc
	 */
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
}
