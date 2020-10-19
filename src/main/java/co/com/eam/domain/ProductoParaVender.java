package co.com.eam.domain;

public class ProductoParaVender extends Producto {
    private Float cantidad;

    public ProductoParaVender(String nombre, String codigo, Float precioUnitario, Float cantidadProducto, Integer id_producto, String marca,Double precioCompra,Float cantidad) {
        super(id_producto,cantidadProducto,marca,nombre,codigo,precioCompra,precioUnitario);
        this.cantidad = cantidad;
    }

    public ProductoParaVender(String nombre, String codigo, Float precioUnitario, Float cantidadProducto, String marca, Double precioCompra, Float cantidad) {
        super(cantidadProducto,marca,nombre,codigo,precioCompra,precioUnitario);
        this.cantidad = cantidad;
    }

    public void aumentarCantidad() {
        this.cantidad++;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public Float getTotal() {
        return this.getPrecioUnitario() * this.cantidad;
    }
}
