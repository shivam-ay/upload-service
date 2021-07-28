package com.example.demo.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.google.common.net.HttpHeaders;

import io.jsonwebtoken.Jwts;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@NoArgsConstructor
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter>
{
	@Autowired
	private Environment env;
	
	public static class Config
	{
		//Put configuration properties here
	}
	
	@Override
	public GatewayFilter apply(AuthorizationFilter config) 
	{
		return (exchange, filterChain) -> {
			ServerHttpRequest request = exchange.getRequest();
			if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
				return onError(exchange,"No Authorization header",HttpStatus.UNAUTHORIZED);
			String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION ).get(0);
			String jwtToken = authorizationHeader.replace("Bearer", "");

			if(!isJwtValid(jwtToken))
				return onError(exchange,"Jwt token is not valid",HttpStatus.UNAUTHORIZED);
			return filterChain.filter(exchange);
		};
	}
	
	private Boolean isJwtValid(String jwtToken)
	{
		String subject = Jwts.parser()
							.setSigningKey(env.getProperty("token.secret"))
							.parseClaimsJws(jwtToken)
							.getBody()
							.getSubject();
		if(subject == null || subject.isEmpty())
			return false;
		else
			return true;
	}
	
	private Mono<Void> onError(ServerWebExchange exchange, String msg, HttpStatus status)
	{
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(status);
		return response.setComplete();
	}
	
	
}
