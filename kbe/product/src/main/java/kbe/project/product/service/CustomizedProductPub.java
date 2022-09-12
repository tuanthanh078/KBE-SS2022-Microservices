package kbe.project.product.service;

import kbe.project.dto.Price;
import kbe.project.product.model.CustomizedProduct;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CustomizedProductPub {

    private final AmqpTemplate amqpTemplate;
    private final String customizedProductsTopicExchange;

    public CustomizedProductPub(final AmqpTemplate amqpTemplate,
                             @Value("${amqp.exchange.customizedProducts}")
                             final String customizedProductsTopicExchange) {
        this.amqpTemplate = amqpTemplate;
        this.customizedProductsTopicExchange = customizedProductsTopicExchange;
    }

    public Price getPrice(final CustomizedProduct customizedProduct) {
        String routingKey = "customizedProducts.price";
        Price price = (Price) amqpTemplate.convertSendAndReceive(customizedProductsTopicExchange,
                                    routingKey, customizedProduct.getSelectedComponents());
        System.out.println(price.toString());
        return price;
    }

}