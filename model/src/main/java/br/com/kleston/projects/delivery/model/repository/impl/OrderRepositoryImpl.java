package br.com.kleston.projects.delivery.model.repository.impl;

import br.com.kleston.projects.delivery.model.dtos.OrderDTO;
import br.com.kleston.projects.delivery.model.jooq.tables.Orders;
import br.com.kleston.projects.delivery.model.repository.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl extends BaseRepositoryImpl<Orders, OrderDTO> implements OrderRepository {
    public static final Orders table = Orders.ORDERS;
    public static final Class<OrderDTO> dtoClass = OrderDTO.class;

    public OrderRepositoryImpl() {
        super( table, dtoClass );
    }
}