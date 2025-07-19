
package cl.duoc.azuread.ejemplo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cliente;
    private Double monto;
    private Date fecha;

    private String urlS3; // Enlace al archivo subido a S3
}
