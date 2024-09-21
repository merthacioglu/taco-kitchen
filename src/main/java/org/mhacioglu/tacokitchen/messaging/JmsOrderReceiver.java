package org.mhacioglu.tacokitchen.messaging;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.mhacioglu.tacokitchen.model.TacoOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JmsOrderReceiver implements OrderReceiver {
    private final JmsTemplate jmsTemplate;
    private final MessageConverter messageConverter;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jmsTemplate, MessageConverter messageConverter) {
        this.jmsTemplate = jmsTemplate;
        this.messageConverter = messageConverter;
    }

    @Override
    public TacoOrder receiveOrder() throws JMSException {
        Message msg = jmsTemplate.receive("tacoworld.order.queue");
        return (TacoOrder) messageConverter.fromMessage(Objects.requireNonNull(msg));
    }

    @Override
    public TacoOrder receiveAndConvertOrder() {
        return (TacoOrder) jmsTemplate.receiveAndConvert("tacoworld.order.queue");
    }
}
