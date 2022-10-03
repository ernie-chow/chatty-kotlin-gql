package com.ernie.chatty

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.core.Ordered
import org.springframework.context.annotation.Bean
import java.util.*
import javax.servlet.Filter

@SpringBootApplication
class ChattyApplication {
	@Bean
	fun simpleCorsFilter(): FilterRegistrationBean<*> {
		val source = UrlBasedCorsConfigurationSource()
		val config = CorsConfiguration()
		config.allowCredentials = true
		// *** URL below needs to match the Vue client URL and port ***
		config.allowedOrigins = Collections.singletonList("http://localhost:3000")
		config.allowedMethods = Collections.singletonList("*")
		config.allowedHeaders = Collections.singletonList("*")
		source.registerCorsConfiguration("/**", config)
		val bean = FilterRegistrationBean<Filter>(CorsFilter(source))
		bean.order = Ordered.HIGHEST_PRECEDENCE
		return bean
	}
}

fun main(args: Array<String>) {
	runApplication<ChattyApplication>(*args)
}
