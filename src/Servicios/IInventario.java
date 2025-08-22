package Servicios;

import Modelos.IProducto;
import Modelos.Producto;

public interface IInventario {
    void agregarProducto(Producto p);
    IProducto buscarProducto(String codigo);
    void actualizarStock(String codigo, int cantidad);
    void mostrarInventario();
}