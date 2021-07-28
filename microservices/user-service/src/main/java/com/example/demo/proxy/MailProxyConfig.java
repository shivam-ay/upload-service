package com.example.demo.proxy;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import feign.codec.Encoder;

public class MailProxyConfig 
{
	public Encoder feignEncoder()
	{
		@SuppressWarnings("rawtypes")
		HttpMessageConverter jacksonConvertor = new MappingJackson2HttpMessageConverter();
		ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConvertor);
		return new SpringEncoder(objectFactory);
	}
}
