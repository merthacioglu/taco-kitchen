package org.mhacioglu.tacokitchen.messaging;

import jakarta.jms.JMSException;
import org.mhacioglu.tacokitchen.model.TacoOrder;

public interface OrderReceiver {
    TacoOrder receiveOrder() throws JMSException;
    TacoOrder receiveAndConvertOrder() throws JMSException;
}
