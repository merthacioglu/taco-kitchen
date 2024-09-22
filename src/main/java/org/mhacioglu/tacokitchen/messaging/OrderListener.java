package org.mhacioglu.tacokitchen.messaging;

import org.mhacioglu.tacokitchen.model.TacoOrder;
import org.mhacioglu.tacokitchen.web.KitchenUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {
    private final KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @KafkaListener(topics="tacoworld.orders.topic")
    public void handle(TacoOrder order) {
        ui.displayOrder(order);
    }


}
