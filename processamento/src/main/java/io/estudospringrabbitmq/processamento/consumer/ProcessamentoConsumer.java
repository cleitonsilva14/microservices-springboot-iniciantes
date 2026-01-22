package io.estudospringrabbitmq.processamento.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import io.estudospringrabbitmq.processamento.dto.PedidoDTO;

@Component
public class ProcessamentoConsumer {
    
    @RabbitListener(queues = "${broker.queue.processamento.name}")
    public void listenerProcessamentoQueue(PedidoDTO pedidoDTO){
        System.out.println(pedidoDTO.descricao());
    }

}
