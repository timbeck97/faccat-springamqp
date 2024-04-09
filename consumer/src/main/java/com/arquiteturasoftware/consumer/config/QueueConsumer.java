package com.arquiteturasoftware.consumer.config;

import com.arquiteturasoftware.consumer.dto.CarDTO;
import com.arquiteturasoftware.consumer.model.Car;
import com.arquiteturasoftware.consumer.repository.CarRepository;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Optional;

@Configuration
public class QueueConsumer {

    @Autowired
    private CarRepository carRepository;

    @RabbitListener(queues = {"${queue.save.car}"})
    public void save(@Payload CarDTO dto) {
        System.out.println("SAVE");
        System.out.println("Message " + dto);
        Car car=new Car();
        car.setChassi(dto.getChassi());
        car.setColor(dto.getColor());
        car.setModelo(dto.getModelo());
        car.setYear(dto.getYear());
        carRepository.save(car);
    }
    @RabbitListener(queues = {"${queue.update.car}"})
    public void receive(@Payload CarDTO dto) throws IllegalAccessException {
        System.out.println("UPDATE");
        System.out.println("Message " + dto);
        if(dto.getId()!=null){
            Car car=carRepository.findById(dto.getId()).get();
            car.setChassi(dto.getChassi());
            car.setColor(dto.getColor());
            car.setModelo(dto.getModelo());
            car.setYear(dto.getYear());
            carRepository.save(car);
        }
    }
    @RabbitListener(queues = {"${queue.get.car}"})
    public void getAll(@Payload Long id){
        System.out.println("GET ALL");
        carRepository.findAll().forEach(System.out::println);
        System.out.println("---------------");
        System.out.println(id);
        if(id!=null){
            Optional<Car> byId = carRepository.findById(id);
            if(byId.isPresent()){
                System.out.println(byId.get());
            }
        }
    }

}
