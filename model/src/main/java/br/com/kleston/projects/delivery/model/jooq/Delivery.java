/*
 * This file is generated by jOOQ.
 */
package br.com.kleston.projects.delivery.model.jooq;


import br.com.kleston.projects.delivery.model.jooq.tables.Account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Delivery extends SchemaImpl {

    private static final long serialVersionUID = -75917386;

    /**
     * The reference instance of <code>delivery</code>
     */
    public static final Delivery DELIVERY = new Delivery();

    /**
     * The table <code>delivery.account</code>.
     */
    public final Account ACCOUNT = br.com.kleston.projects.delivery.model.jooq.tables.Account.ACCOUNT;

    /**
     * No further instances allowed
     */
    private Delivery() {
        super("delivery", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Account.ACCOUNT);
    }
}