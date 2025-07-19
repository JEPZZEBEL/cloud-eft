package cl.duoc.azuread.ejemplo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/microservicio")
public class MicroservicioController {

    @GetMapping("/{id}")
    public ResponseEntity<String> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok("Microservicio activo. ID recibido: " + id);
    }
}
