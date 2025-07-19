
package cl.duoc.azuread.ejemplo.repository;

import cl.duoc.azuread.ejemplo.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByCliente(String cliente);
}
