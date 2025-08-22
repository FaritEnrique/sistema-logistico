package Servicios;

import Modelos.IDocumento;
import Modelos.Requisicion;
import Modelos.Cotizacion;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorDocumentos {
    private final List<IDocumento> documentos = new ArrayList<>();

    public void agregarDocumento(IDocumento d) {
        documentos.add(d);
        System.out.println("✅ Documento '" + d.getCodigo() + "' agregado.");
    }

    public void mostrarDocumentos() {
        if (documentos.isEmpty()) {
            System.out.println("No hay documentos.");
        } else {
            documentos.forEach(IDocumento::mostrar);
        }
    }

    public Optional<IDocumento> buscarDocumento(String codigo) {
        return documentos.stream()
                .filter(d -> d.getCodigo().equalsIgnoreCase(codigo))
                .findFirst();
    }

    public List<IDocumento> getRequisiciones() {
        List<IDocumento> requisiciones = new ArrayList<>();
        for (IDocumento d : documentos) {
            if (d instanceof Requisicion) {
                requisiciones.add(d);
            }
        }
        return requisiciones;
    }

    public List<IDocumento> getCotizaciones() {
        List<IDocumento> cotizaciones = new ArrayList<>();
        for (IDocumento d : documentos) {
            if (d instanceof Cotizacion) {
                cotizaciones.add(d);
            }
        }
        return cotizaciones;
    }
}