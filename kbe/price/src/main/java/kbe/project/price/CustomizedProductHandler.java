package kbe.project.price;

import kbe.project.dto.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Service
public class CustomizedProductHandler {

    private final PriceService priceService;

    @RabbitListener(queues = "${amqp.queue.price}")
    Price handleCustomizedProduct(List<SelectedComponents> selectedComponents) {
        log.info("Customized Product received: {}", selectedComponents.toString());
        try {
            Price price = priceService.calculatePrice(selectedComponents);
            System.out.println(price);
            return price;
        } catch (final Exception e) {
            log.error("Error when trying to process CustomizedProduct", e);
            // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
