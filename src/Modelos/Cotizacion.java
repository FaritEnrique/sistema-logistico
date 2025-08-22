package Modelos;

public class Cotizacion extends Documento {

    private static int contadorCotizaciones = 1;

    private String proveedor;
    private IProducto producto;
    private int cantidad;
    private double precioUnitario;

    // Solo un constructor que autogenera el código
    public Cotizacion(String proveedor, IProducto producto, int cantidad, double precioUnitario) {
        super("COT-" + String.format("%03d", contadorCotizaciones++));
        this.proveedor = proveedor;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public double calcularTotal() {
        return cantidad * precioUnitario;
    }

    public int getCantidad() { return cantidad; }
    public IProducto getProducto() { return producto; }

    @Override
    public void mostrar() {
        System.out.println("Cotización: " + codigo + " | Proveedor: " + proveedor);
        System.out.println(producto.getNombre() + " | Cant: " + cantidad + " | Precio unitario: $" + precioUnitario +
                " | Total: $" + calcularTotal());
    }
}