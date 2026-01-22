package io.estudospringrabbitmq.pedido.controller;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.estudospringrabbitmq.pedido.model.Pedido;
import io.estudospringrabbitmq.pedido.service.PedidoService;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    
    private final PedidoService pedidoService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.queue.processamento.name}")
    private String routingKey;


    @PostMapping
    public String criarPedido(@RequestBody Pedido pedido) {
        Pedido pedidoSalvo = pedidoService.salvarPedido(pedido);

        rabbitTemplate.convertAndSend("", routingKey, pedidoSalvo);

        return "Pedido salvo e enviado para processamento %s".formatted(pedido.getDescricao());
    }
    


    @GetMapping
    public List<Pedido> listarPedidos(){
        return pedidoService.listarPedidos();
    }


}
