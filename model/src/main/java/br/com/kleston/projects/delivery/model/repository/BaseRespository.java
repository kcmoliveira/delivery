package br.com.kleston.projects.delivery.model.repository;

import br.com.kleston.projects.delivery.model.dtos.BaseDTO;
import org.jooq.TableField;

import java.io.Serializable;
import java.util.List;

public interface BaseRespository<E extends BaseDTO> {
    List<E> findAll();
    <V extends Serializable> List<E> findBy(TableField field, V value);
    E save(E record);
    void delete(E record);
}