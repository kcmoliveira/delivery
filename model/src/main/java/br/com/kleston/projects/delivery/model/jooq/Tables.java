/*
 * This file is generated by jOOQ.
 */
package br.com.kleston.projects.delivery.model.jooq;


import br.com.kleston.projects.delivery.model.jooq.tables.Accounts;
import br.com.kleston.projects.delivery.model.jooq.tables.Orders;
import br.com.kleston.projects.delivery.model.jooq.tables.OrdersProducts;
import br.com.kleston.projects.delivery.model.jooq.tables.Products;
import br.com.kleston.projects.delivery.model.jooq.tables.Restaurants;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in delivery
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>delivery.accounts</code>.
     */
    public static final Accounts ACCOUNTS = br.com.kleston.projects.delivery.model.jooq.tables.Accounts.ACCOUNTS;

    /**
     * The table <code>delivery.orders</code>.
     */
    public static final Orders ORDERS = br.com.kleston.projects.delivery.model.jooq.tables.Orders.ORDERS;

    /**
     * The table <code>delivery.orders_products</code>.
     */
    public static final OrdersProducts ORDERS_PRODUCTS = br.com.kleston.projects.delivery.model.jooq.tables.OrdersProducts.ORDERS_PRODUCTS;

    /**
     * The table <code>delivery.products</code>.
     */
    public static final Products PRODUCTS = br.com.kleston.projects.delivery.model.jooq.tables.Products.PRODUCTS;

    /**
     * The table <code>delivery.restaurants</code>.
     */
    public static final Restaurants RESTAURANTS = br.com.kleston.projects.delivery.model.jooq.tables.Restaurants.RESTAURANTS;
}
