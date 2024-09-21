package org.mhacioglu.tacokitchen.messaging;

import org.mhacioglu.tacokitchen.messaging.web.KitchenUI;
import org.mhacioglu.tacokitchen.model.TacoOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Profile("jms-listener")
@Component
public class OrderListener {
    private final KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @JmsListener(destination = "tacoworld.order.queue")
    public void receiveOrder(TacoOrder order) {
        ui.displayOrder(order);
    }


}
