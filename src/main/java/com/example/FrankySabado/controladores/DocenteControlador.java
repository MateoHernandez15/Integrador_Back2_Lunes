package com.example.FrankySabado.controladores;

import com.example.FrankySabado.modelos.dtos.DocenteDTO;
import com.example.FrankySabado.servicios.DocenteServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/docentes")
public class DocenteControlador {

    @Autowired
    DocenteServicio docenteServicio;

    @PostMapping
    public ResponseEntity<?> guardarDocente(@Valid @RequestBody DocenteDTO datosDocente) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(docenteServicio.guardarDocente(datosDocente));
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
                    .body(docenteServicio.buscarTodos());
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
                    .body(docenteServicio.buscarPorId(id));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDocente(@PathVariable Integer id, @Valid @RequestBody DocenteDTO datosNuevos) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(docenteServicio.actualizarDocente(id, datosNuevos));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDocente(@PathVariable Integer id) {
        try {
            if (docenteServicio.eliminarDocente(id)) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Docente eliminado exitosamente");
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("No se pudo eliminar el docente");
            }
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}