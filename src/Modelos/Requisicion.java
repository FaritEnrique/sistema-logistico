package Modelos;

import java.util.HashMap;
import java.util.Map;

public class Requisicion extends Documento {

    // Se agrega el contador estático para autogenerar códigos
    private static int contadorRequisiciones = 1;

    // Se elimina el constructor que recibía el 'String codigo'
    // y se usa uno que autogenere el código
    public Requisicion() {
        super("REQ-" + String.format("%03d", contadorRequisiciones++));
    }

    private final Map<IProducto, Integer> productos = new HashMap<>();

    public void agregarProducto(IProducto p, int cantidad) {
        productos.put(p, cantidad);
    }

    public Map<IProducto, Integer> getProductos() {
        return productos;
    }

    @Override
    public void mostrar() {
        System.out.println("Requisición: " + codigo);
        productos.forEach((p, c) -> System.out.println(p.getNombre() + " | Cant: " + c));
    }
}