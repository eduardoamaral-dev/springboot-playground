package com.example.springbootplayground.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Configuration
public class RMQConfig {

    @Autowired
    private Environment env;

    @Bean
    public CachingConnectionFactory connectionFactory() throws URISyntaxException {
        CachingConnectionFactory factory;
        String username = URLEncoder.encode(env.getRequiredProperty("amqp.rmq.username"), StandardCharsets.UTF_8);
        String password = URLEncoder.encode(env.getRequiredProperty("amqp.rmq.password"), StandardCharsets.UTF_8);
        String host = URLEncoder.encode(env.getRequiredProperty("amqp.rmq.host"), StandardCharsets.UTF_8);
        String virtualhost = URLEncoder.encode(env.getRequiredProperty("amqp.rmq.virtual.host"), StandardCharsets.UTF_8);
        String port = URLEncoder.encode(env.getRequiredProperty("amqp.rmq.port"), StandardCharsets.UTF_8);
        factory = new CachingConnectionFactory(new URI("amqps://" + username + ":" + password + "@" + host + ":" + port + "/" + virtualhost));

        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() throws URISyntaxException {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }
}
