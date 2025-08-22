package Modelos;

import java.util.Map;

public class NotaIngreso extends Documento {

    private static int contadorNotasIngreso = 1;
    private final Map<IProducto, Integer> productos;

    // Solo un constructor que autogenera el código
    public NotaIngreso(Map<IProducto, Integer> productos) {
        super("NI-" + String.format("%03d", contadorNotasIngreso++));
        this.productos = productos;
    }

    @Override
    public void mostrar() {
        System.out.println("Nota de Ingreso: " + codigo);
        productos.forEach((p, c) -> System.out.println(p.getNombre() + " | Cantidad ingresada: " + c));
    }
}