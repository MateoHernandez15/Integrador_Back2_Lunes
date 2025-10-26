package com.example.FrankySabado.controladores;

import com.example.FrankySabado.modelos.dtos.FamiliarDTO;
import com.example.FrankySabado.servicios.FamiliarServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/familiares")
public class FamiliarControlador {

    @Autowired
    FamiliarServicio familiarServicio;

    @PostMapping
    public ResponseEntity<?> guardarFamiliar(@Valid @RequestBody FamiliarDTO datosFamiliar) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(familiarServicio.guardarFamiliar(datosFamiliar));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> buscarTodos() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(familiarServicio.buscarTodos());
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(familiarServicio.buscarPorId(id));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarFamiliar(@PathVariable Integer id, @Valid @RequestBody FamiliarDTO datosNuevos) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(familiarServicio.actualizarFamiliar(id, datosNuevos));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarFamiliar(@PathVariable Integer id) {
        try {
            if (familiarServicio.eliminarFamiliar(id)) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Familiar eliminado exitosamente");
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("No se pudo eliminar el familiar");
            }
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}