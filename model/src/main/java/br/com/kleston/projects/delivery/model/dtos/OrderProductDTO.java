package br.com.kleston.projects.delivery.model.dtos;

public class OrderProductDTO extends BaseDTO {
    private Long idOrder;
    private Long idProduct;

    public OrderProductDTO() { }

    public Long getIdOrder() {
        return this.idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdProduct() {
        return this.idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }
}