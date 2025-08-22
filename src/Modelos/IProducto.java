package Modelos;

public interface IProducto {
    String getNombre();
    String getCodigo();
    String getCategoria();
    int getStock();
    void setStock(int stock);
    double getPrecio();
    void mostrarInfo();
}