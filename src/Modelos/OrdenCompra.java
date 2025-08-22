package Modelos;

import java.util.ArrayList;
import java.util.List;

public class OrdenCompra extends Documento {

    private static int contadorOrdenes = 1;

    // Solo un constructor que autogenera el código
    public OrdenCompra() {
        super("OC-" + String.format("%03d", contadorOrdenes++));
    }

    private final List<Cotizacion> items = new ArrayList<>();

    public void agregarItem(Cotizacion c) { items.add(c); }

    @Override
    public void mostrar() {
        System.out.println("Orden de Compra: " + codigo);
        items.forEach(Cotizacion::mostrar);
    }
}