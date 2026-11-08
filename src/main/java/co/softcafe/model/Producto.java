package co.softcafe.model;

import java.math.BigDecimal;

/** Representa un producto comercializado por SoftCafé. */
public class Producto {
    private int id;
    private String codigo;
    private String nombre;
    private BigDecimal precio;
    private int stock;

    public Producto(int id, String codigo, String nombre, BigDecimal precio, int stock) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(String codigo, String nombre, BigDecimal precio, int stock) {
        this(0, codigo, nombre, precio, stock);
    }

    public int getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public BigDecimal getPrecio() { return precio; }
    public int getStock() { return stock; }
    public void setId(int id) { this.id = id; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return String.format("ID: %d | Código: %s | Producto: %s | Precio: $%s | Stock: %d",
                id, codigo, nombre, precio.toPlainString(), stock);
    }
}
