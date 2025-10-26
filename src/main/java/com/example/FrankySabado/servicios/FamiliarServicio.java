package com.example.FrankySabado.servicios;

import com.example.FrankySabado.ayudas.Estados;
import com.example.FrankySabado.ayudas.Roles;
import com.example.FrankySabado.modelos.Familiar;
import com.example.FrankySabado.modelos.Usuario;
import com.example.FrankySabado.modelos.dtos.FamiliarDTO;
import com.example.FrankySabado.modelos.mapas.IMapaFamiliar;
import com.example.FrankySabado.repositorios.IFamiliarRepositorio;
import com.example.FrankySabado.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamiliarServicio {

    @Autowired
    IFamiliarRepositorio familiarRepositorio;

    @Autowired
    IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    IMapaFamiliar mapa;

    public FamiliarDTO guardarFamiliar(FamiliarDTO datosFamiliar) throws Exception {
        try {
            Optional<Usuario> usuarioBuscado = usuarioRepositorio.findByCorreo(datosFamiliar.getCorreo());
            if (usuarioBuscado.isPresent()) {
                throw new Exception("El correo ya está registrado");
            }

            Usuario usuario = mapa.convertirDTOAUsuario(datosFamiliar);
            usuario.setRol(Roles.Familiar);
            usuario.setEstado(Estados.Activo);
            Usuario usuarioGuardado = usuarioRepositorio.save(usuario);

            Familiar familiar = mapa.convertirDTOAFamiliar(datosFamiliar);
            familiar.setUsuario(usuarioGuardado);

            Familiar familiarGuardado = familiarRepositorio.save(familiar);
            return mapa.convertirEntidadADTO(familiarGuardado);

        } catch (Exception error) {
            throw new Exception("Fallamos guardando el familiar: " + error.getMessage());
        }
    }

    public List<FamiliarDTO> buscarTodos() throws Exception {
        try {
            return mapa.convertirListaA_DTO(familiarRepositorio.findAll());
        } catch (Exception error) {
            throw new Exception("Fallamos buscando los familiares: " + error.getMessage());
        }
    }

    public FamiliarDTO buscarPorId(Integer id) throws Exception {
        try {
            Optional<Familiar> familiarBuscado = familiarRepositorio.findById(id);
            if (familiarBuscado.isEmpty()) {
                throw new Exception("Familiar no encontrado");
            }
            return mapa.convertirEntidadADTO(familiarBuscado.get());
        } catch (Exception error) {
            throw new Exception("Fallamos buscando el familiar: " + error.getMessage());
        }
    }

    public FamiliarDTO actualizarFamiliar(Integer id, FamiliarDTO datosNuevos) throws Exception {
        try {
            Optional<Familiar> familiarBuscado = familiarRepositorio.findById(id);
            if (familiarBuscado.isEmpty()) {
                throw new Exception("Familiar no encontrado para actualizar");
            }

            Familiar familiarExistente = familiarBuscado.get();
            Usuario usuarioExistente = familiarExistente.getUsuario();

            usuarioExistente.setNombre(datosNuevos.getNombre());
            usuarioExistente.setCorreo(datosNuevos.getCorreo());
            usuarioExistente.setContraseña(datosNuevos.getContraseña());
            usuarioRepositorio.save(usuarioExistente);

            familiarExistente.setParentesco(datosNuevos.getParentesco());
            familiarExistente.setTelefono(datosNuevos.getTelefono());
            familiarExistente.setDireccion(datosNuevos.getDireccion());

            Familiar familiarActualizado = familiarRepositorio.save(familiarExistente);
            return mapa.convertirEntidadADTO(familiarActualizado);

        } catch (Exception error) {
            throw new Exception("Fallamos actualizando el familiar: " + error.getMessage());
        }
    }

    public boolean eliminarFamiliar(Integer id) throws Exception {
        try {
            Optional<Familiar> familiarBuscado = familiarRepositorio.findById(id);
            if (familiarBuscado.isEmpty()) {
                throw new Exception("Familiar no encontrado para eliminar");
            }
            familiarRepositorio.deleteById(id);
            return true;
        } catch (Exception error) {
            throw new Exception("Fallamos eliminando el familiar: " + error.getMessage());
        }
    }
}