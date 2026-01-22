package io.estudospringrabbitmq.pedido.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.estudospringrabbitmq.pedido.model.ItemPedido;
import io.estudospringrabbitmq.pedido.model.Pedido;
import io.estudospringrabbitmq.pedido.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;

    public Pedido salvarPedido(Pedido pedido){
        if(pedido.getItens() != null){
            for(ItemPedido item : pedido.getItens()){
                item.setPedido(pedido);
            }
        }
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos(){
        return pedidoRepository.findAll();
    }

        

}
