package Servicios;

import Modelos.IProducto;
import Modelos.Producto;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class Inventario implements IInventario {
    private final Map<String, IProducto> productos = new HashMap<>();

    @Override
    public void agregarProducto(Producto p) {
        productos.put(p.getCodigo(), p);
    }

    @Override
    public IProducto buscarProducto(String codigo) {
        return productos.get(codigo);
    }

    @Override
    public void actualizarStock(String codigo, int cantidad) {
        IProducto p = buscarProducto(codigo);
        if (p != null) {
            p.setStock(p.getStock() + cantidad);
            System.out.println("✅ Stock actualizado para " + p.getNombre() + ".");
        } else {
            System.out.println("❌ Producto con código " + codigo + " no encontrado.");
        }
    }

    @Override
    public void mostrarInventario() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
        } else {
            System.out.println("\n" + "=".repeat(70));
            System.out.println("             INVENTARIO DE PRODUCTOS");
            System.out.println("=".repeat(70));
            productos.values().forEach(IProducto::mostrarInfo);
            System.out.println("=".repeat(70));
        }
    }

    public Collection<IProducto> getProductos() {
        return productos.values();
    }
}