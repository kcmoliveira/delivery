package br.com.kleston.projects.delivery.model.dtos;

import java.io.Serializable;

public abstract class BaseDTO implements Serializable {
    private Long id;

    public BaseDTO() { }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}