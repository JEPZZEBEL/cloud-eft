
package cl.duoc.azuread.ejemplo.service;

import cl.duoc.azuread.ejemplo.model.Factura;
import cl.duoc.azuread.ejemplo.repository.FacturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public Factura crearFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    public List<Factura> obtenerHistorialPorCliente(String cliente) {
        return facturaRepository.findByCliente(cliente);
    }

    public Optional<Factura> obtenerFacturaPorId(Long id) {
        return facturaRepository.findById(id);
    }

    public Factura actualizarFactura(Long id, Factura nuevaFactura) {
        return facturaRepository.findById(id).map(factura -> {
            factura.setCliente(nuevaFactura.getCliente());
            factura.setFecha(nuevaFactura.getFecha());
            factura.setMonto(nuevaFactura.getMonto());
            factura.setUrlS3(nuevaFactura.getUrlS3());
            return facturaRepository.save(factura);
        }).orElse(null);
    }

    public void eliminarFactura(Long id) {
        facturaRepository.deleteById(id);
    }
}
