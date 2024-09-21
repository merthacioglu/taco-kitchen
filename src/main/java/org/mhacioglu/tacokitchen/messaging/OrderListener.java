package org.mhacioglu.tacokitchen.messaging;

import org.mhacioglu.tacokitchen.model.TacoOrder;
import org.mhacioglu.tacokitchen.web.KitchenUI;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    private final KitchenUI ui;

    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @RabbitListener(queues = "tacoworld.order.queue")
    public void receiveOrder(TacoOrder order) {
        ui.displayOrder(order);
    }
}
