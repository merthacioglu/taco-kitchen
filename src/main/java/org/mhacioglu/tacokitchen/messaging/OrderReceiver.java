package org.mhacioglu.tacokitchen.messaging;

import org.mhacioglu.tacokitchen.model.TacoOrder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderReceiver {

    private final RabbitTemplate rabbitTemplate;
    private final MessageConverter messageConverter;

    public OrderReceiver(RabbitTemplate rabbitTemplate, MessageConverter messageConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageConverter = messageConverter;
    }

    public TacoOrder receiveOrder() {
        Message message = rabbitTemplate.receive("tacoworld.order");
        Optional<Message> optionalMessage = Optional.ofNullable(message);
        return optionalMessage.map(value -> (TacoOrder) messageConverter.fromMessage(value)).orElse(null);
    }

    public TacoOrder receiveAndConvertOrder() {
        return (TacoOrder) rabbitTemplate.receiveAndConvert("tacoworld.order.queue");
    }

    public TacoOrder receiveOrder2() {
        return rabbitTemplate.receiveAndConvert("tacoworld.order.queue",
                new ParameterizedTypeReference<>() {
                });
    }


}
