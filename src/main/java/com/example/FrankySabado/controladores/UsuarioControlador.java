package com.example.FrankySabado.controladores;

import com.example.FrankySabado.modelos.Usuario;
import com.example.FrankySabado.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio servicio;

    //Por cada metodo del servicio se programa
    //un metodo del controlador
    @PostMapping
    public ResponseEntity<?> activarServicioGuardar(@RequestBody Usuario datosQueEnviaJossy){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.guardarUsuario(datosQueEnviaJossy));
        }catch(Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> activarServicioBuscarTodos(){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.buscarUsuarios());
        }catch(Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> activarServicioBuscarUsuarioID(@PathVariable Integer id){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.buscarUsuarioPorId(id));
        }catch(Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    @GetMapping("/correo")
    public ResponseEntity<?>activarServicioBuscarCorreo(@RequestBody Usuario datosQueMandaJossy){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.buscarUsuarioPorCorreo(datosQueMandaJossy.getCorreo()));
        }catch(Exception error){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }

    }

}
