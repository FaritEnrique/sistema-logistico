package Modelos;

import java.util.Map;

public class NotaSalida extends Documento {

    private static int contadorNotasSalida = 1;
    private final Map<IProducto, Integer> productos;

    // Solo un constructor que autogenera el código
    public NotaSalida(Map<IProducto, Integer> productos) {
        super("NS-" + String.format("%03d", contadorNotasSalida++));
        this.productos = productos;
    }

    @Override
    public void mostrar() {
        System.out.println("Nota de Salida: " + codigo);
        productos.forEach((p, c) -> System.out.println(p.getNombre() + " | Cantidad salida: " + c));
    }
}