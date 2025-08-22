import Modelos.*;
import Servicios.*;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IInventario inventario = new Inventario();
        GestorDocumentos gestor = new GestorDocumentos();

        int opcion;
        do {
            System.out.println("\n--- Sistema de Inventarios y Documentos ---");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Mostrar Inventario");
            System.out.println("3. Generar Requisición");
            System.out.println("4. Mostrar Documentos");
            System.out.println("5. Crear Cotización");
            System.out.println("6. Emitir Orden de Compra");
            System.out.println("7. Emitir Nota de Ingreso");
            System.out.println("8. Emitir Nota de Salida");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Categoría: ");
                    String cat = sc.nextLine();
                    System.out.print("Stock inicial: ");
                    int stock = sc.nextInt();
                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    sc.nextLine();

                    Producto p = new Producto(nombre, cat, stock, precio);
                    inventario.agregarProducto(p);
                    System.out.println("✅ Producto agregado con código: " + p.getCodigo());
                }
                case 2 -> inventario.mostrarInventario();
                case 3 -> {
                    Requisicion r = new Requisicion();
                    System.out.println("Requisición " + r.getCodigo() + " en proceso.");

                    while (true) {
                        System.out.print("Código del producto (o 'fin' para terminar): ");
                        String codProd = sc.nextLine();
                        if (codProd.equalsIgnoreCase("fin")) {
                            break;
                        }
                        IProducto p = inventario.buscarProducto(codProd);
                        if (p != null) {
                            System.out.print("Cantidad: ");
                            int cant = sc.nextInt();
                            sc.nextLine();
                            r.agregarProducto(p, cant);
                        } else {
                            System.out.println("❌ Producto no encontrado.");
                        }
                    }
                    gestor.agregarDocumento(r);
                }
                case 4 -> gestor.mostrarDocumentos();
                case 5 -> {
                    System.out.print("Proveedor: ");
                    String prov = sc.nextLine();
                    System.out.print("Código del producto: ");
                    String codProd = sc.nextLine();
                    IProducto p = inventario.buscarProducto(codProd);
                    if (p != null) {
                        System.out.print("Cantidad: ");
                        int cant = sc.nextInt();
                        System.out.print("Precio unitario: ");
                        double precio = sc.nextDouble();
                        sc.nextLine();

                        Cotizacion c = new Cotizacion(prov, p, cant, precio);
                        gestor.agregarDocumento(c);
                        System.out.println("✅ Cotización agregada con código: " + c.getCodigo());
                    } else {
                        System.out.println("❌ Producto no encontrado.");
                    }
                }
                case 6 -> {
                    OrdenCompra oc = new OrdenCompra();
                    for (IDocumento d : gestor.getCotizaciones()) {
                        oc.agregarItem((Cotizacion) d);
                    }
                    gestor.agregarDocumento(oc);
                    System.out.println("✅ Orden de compra emitida con código: " + oc.getCodigo());
                    oc.mostrar();
                }
                case 7 -> {
                    System.out.println("Requisiciones disponibles:");
                    gestor.getRequisiciones().forEach(IDocumento::mostrar);
                    System.out.print("Ingrese el código de la requisición a procesar: ");
                    String codReq = sc.nextLine();
                    Optional<IDocumento> docOpt = gestor.buscarDocumento(codReq);

                    // Se reemplazó el casting explícito con una variable de patrón 'r'
                    if (docOpt.isPresent() && docOpt.get() instanceof Requisicion r) {
                        NotaIngreso ni = new NotaIngreso(r.getProductos());
                        gestor.agregarDocumento(ni);
                        r.getProductos().forEach((prod, cant) -> inventario.actualizarStock(prod.getCodigo(), cant));
                        System.out.println("✅ Nota de ingreso emitida con código: " + ni.getCodigo());
                    } else {
                        System.out.println("❌ Requisición no encontrada o código incorrecto.");
                    }
                }
                case 8 -> {
                    System.out.println("Requisiciones disponibles:");
                    gestor.getRequisiciones().forEach(IDocumento::mostrar);
                    System.out.print("Ingrese el código de la requisición a procesar: ");
                    String codReq = sc.nextLine();
                    Optional<IDocumento> docOpt = gestor.buscarDocumento(codReq);

                    // Se reemplazó el casting explícito con una variable de patrón 'r'
                    if (docOpt.isPresent() && docOpt.get() instanceof Requisicion r) {
                        NotaSalida ns = new NotaSalida(r.getProductos());
                        gestor.agregarDocumento(ns);
                        r.getProductos().forEach((prod, cant) -> inventario.actualizarStock(prod.getCodigo(), -cant));
                        System.out.println("✅ Nota de salida emitida con código: " + ns.getCodigo());
                    } else {
                        System.out.println("❌ Requisición no encontrada o código incorrecto.");
                    }
                }
                case 9 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 9);
        System.out.println("El programa ha finalizado correctamente.");
        sc.close();
    }
}