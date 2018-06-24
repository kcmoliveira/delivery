package br.com.kleston.projects.delivery.model.repository.impl;

import br.com.kleston.projects.delivery.model.dtos.OrderProductDTO;
import br.com.kleston.projects.delivery.model.jooq.tables.OrdersProducts;
import br.com.kleston.projects.delivery.model.repository.OrderProductRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderProductRepositoryImpl extends BaseRepositoryImpl<OrdersProducts, OrderProductDTO> implements OrderProductRepository {
    public static final OrdersProducts table = OrdersProducts.ORDERS_PRODUCTS;
    public static final Class<OrderProductDTO> dtoClass = OrderProductDTO.class;

    public OrderProductRepositoryImpl() {
        super( table, dtoClass );
    }
}