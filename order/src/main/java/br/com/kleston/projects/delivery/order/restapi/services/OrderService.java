package br.com.kleston.projects.delivery.order.restapi.services;

import br.com.kleston.projects.delivery.model.dtos.OrderDTO;
import br.com.kleston.projects.delivery.model.dtos.OrderProductDTO;
import br.com.kleston.projects.delivery.model.repository.OrderProductRepository;
import br.com.kleston.projects.delivery.model.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Transactional
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        OrderDTO orderSaved = this.orderRepository.save( orderDTO );

        orderDTO.getOrderProductsList().forEach( productDTO -> {
            productDTO.setIdOrder( orderSaved.getId() );

            OrderProductDTO productSaved = this.orderProductRepository.save( productDTO );

            orderSaved.getOrderProductsList().add( productSaved );
        } );

        return orderSaved;
    }
}