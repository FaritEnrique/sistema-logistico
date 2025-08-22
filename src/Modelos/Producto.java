// Archivo: src/Modelos/Producto.java
package Modelos;

public class Producto implements IProducto {
    private static int contadorProductos = 1; // Contador estático

    private String nombre;
    private String codigo; // Ya no lo recibirá del usuario
    private String categoria;
    private int stock;
    private double precio;

    // Se modifica el constructor para que ya no reciba el código
    public Producto(String nombre, String categoria, int stock, double precio) {
        this.nombre = nombre;
        // Genera el código automáticamente
        this.codigo = "PROD-" + String.format("%03d", contadorProductos++);
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;
    }

    @Override
    public String getNombre() { return nombre; }
    @Override
    public String getCodigo() { return codigo; }
    @Override
    public String getCategoria() { return categoria; }
    @Override
    public int getStock() { return stock; }
    @Override
    public void setStock(int stock) { this.stock = stock; }
    @Override
    public double getPrecio() { return precio; }

    @Override
    public void mostrarInfo() {
        System.out.printf("%-15s | Código: %-10s | Cat: %-12s | Stock: %-4d | Precio: $%.2f%n",
                nombre, codigo, categoria, stock, precio);
    }

}