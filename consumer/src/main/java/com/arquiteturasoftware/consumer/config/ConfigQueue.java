package com.arquiteturasoftware.consumer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigQueue {
    @Value("${queue.save.car}")
    private String save;
    @Value("${queue.update.car}")
    private String update;
    @Value("${queue.get.car}")
    private String get;
    @Bean(value = "${queue.save.car}")
    public Queue save() {
        return new Queue(save, false,false,true);
    }
    @Bean(value = "${queue.update.car}")
    public Queue update() {
        return new Queue(update, false,false,true);
    }
    @Bean(value = "${queue.get.car}")
    public Queue get() {
        return new Queue(get, false,false,true);
    }
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("arquitetura-exchange");
    }
    @Bean
    public Binding binding1(@Qualifier("${queue.save.car}") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("arquitetura-exchange");
    }
    @Bean
    public Binding binding2(@Qualifier("${queue.update.car}") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("arquitetura-exchange");
    }
    @Bean
    public Binding binding3(@Qualifier("${queue.get.car}") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("arquitetura-exchange");
    }
    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
