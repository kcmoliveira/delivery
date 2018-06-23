/*
 * This file is generated by jOOQ.
 */
package br.com.kleston.projects.delivery.model.jooq.tables;


import br.com.kleston.projects.delivery.model.jooq.Delivery;
import br.com.kleston.projects.delivery.model.jooq.Indexes;
import br.com.kleston.projects.delivery.model.jooq.Keys;
import br.com.kleston.projects.delivery.model.jooq.tables.records.ProductsRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Products extends TableImpl<ProductsRecord> {

    private static final long serialVersionUID = -1116290422;

    /**
     * The reference instance of <code>delivery.products</code>
     */
    public static final Products PRODUCTS = new Products();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ProductsRecord> getRecordType() {
        return ProductsRecord.class;
    }

    /**
     * The column <code>delivery.products.id</code>.
     */
    public final TableField<ProductsRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>delivery.products.name</code>.
     */
    public final TableField<ProductsRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>delivery.products.price</code>.
     */
    public final TableField<ProductsRecord, Double> PRICE = createField("price", org.jooq.impl.SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>delivery.products.id_restaurant</code>.
     */
    public final TableField<ProductsRecord, Long> ID_RESTAURANT = createField("id_restaurant", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * Create a <code>delivery.products</code> table reference
     */
    public Products() {
        this(DSL.name("products"), null);
    }

    /**
     * Create an aliased <code>delivery.products</code> table reference
     */
    public Products(String alias) {
        this(DSL.name(alias), PRODUCTS);
    }

    /**
     * Create an aliased <code>delivery.products</code> table reference
     */
    public Products(Name alias) {
        this(alias, PRODUCTS);
    }

    private Products(Name alias, Table<ProductsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Products(Name alias, Table<ProductsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Products(Table<O> child, ForeignKey<O, ProductsRecord> key) {
        super(child, key, PRODUCTS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Delivery.DELIVERY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.PRODUCTS_ID_RESTAURANT, Indexes.PRODUCTS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<ProductsRecord, Long> getIdentity() {
        return Keys.IDENTITY_PRODUCTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ProductsRecord> getPrimaryKey() {
        return Keys.KEY_PRODUCTS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ProductsRecord>> getKeys() {
        return Arrays.<UniqueKey<ProductsRecord>>asList(Keys.KEY_PRODUCTS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<ProductsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ProductsRecord, ?>>asList(Keys.PRODUCTS_IBFK_1);
    }

    public Restaurants restaurants() {
        return new Restaurants(this, Keys.PRODUCTS_IBFK_1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Products as(String alias) {
        return new Products(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Products as(Name alias) {
        return new Products(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Products rename(String name) {
        return new Products(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Products rename(Name name) {
        return new Products(name, null);
    }
}
