package cl.duoc.azuread.ejemplo.controller;

import cl.duoc.azuread.ejemplo.model.Factura;
import cl.duoc.azuread.ejemplo.service.FacturaService;
import cl.duoc.azuread.ejemplo.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    private final S3Service s3Service;
    private final FacturaService facturaService;

    @Autowired
    public FacturaController(S3Service s3Service, FacturaService facturaService) {
        this.s3Service = s3Service;
        this.facturaService = facturaService;
    }

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Factura> crear(@RequestBody Factura factura) {
        return ResponseEntity.ok(facturaService.crearFactura(factura));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Factura> actualizar(@PathVariable Long id, @RequestBody Factura factura) {
        return ResponseEntity.ok(facturaService.actualizarFactura(id, factura));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        facturaService.eliminarFactura(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{cliente}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<Factura>> historial(@PathVariable String cliente) {
        return ResponseEntity.ok(facturaService.obtenerHistorialPorCliente(cliente));
    }

    @GetMapping("/{id}/descargar")
    @PreAuthorize("hasRole('lector')")
    public ResponseEntity<String> descargar(@PathVariable Long id) {
        return facturaService.obtenerFacturaPorId(id)
                .map(factura -> ResponseEntity.ok(factura.getUrlS3()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/subir")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> subirArchivo(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            String url = s3Service.subirArchivo(file);
            facturaService.obtenerFacturaPorId(id).ifPresent(factura -> {
                factura.setUrlS3(url);
                facturaService.crearFactura(factura);
            });
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al subir archivo: " + e.getMessage());
        }
    }
}
