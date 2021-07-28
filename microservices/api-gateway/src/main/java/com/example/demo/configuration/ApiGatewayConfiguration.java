//package com.example.demo.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.example.demo.filter.AuthorizationFilter;
//
//@Configuration
//public class ApiGatewayConfiguration
//{
//	@Autowired
//	private AuthorizationFilter authorizationFilter;
//	
//	@Bean
//	public RouteLocator gatewayRouter(RouteLocatorBuilder builder)
//	{
//		return builder.routes()
//				.route(p ->	p.path("/api/users/*")
//							.and()	
//							//.header("Authorization")
//							//.and()
//							.method("GET","POST")
//							//.filters(f -> f.filter((GatewayFilter) authorizationFilter))
//							.uri("lb://user-service"))
//				.build();
//	}
//}
