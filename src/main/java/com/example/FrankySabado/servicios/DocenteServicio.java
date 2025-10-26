package com.example.FrankySabado.servicios;

import com.example.FrankySabado.ayudas.Estados;
import com.example.FrankySabado.ayudas.Roles;
import com.example.FrankySabado.modelos.Docente;
import com.example.FrankySabado.modelos.Usuario;
import com.example.FrankySabado.modelos.dtos.DocenteDTO;
import com.example.FrankySabado.modelos.mapas.IMapaDocente;
import com.example.FrankySabado.repositorios.IDocenteRepositorio;
import com.example.FrankySabado.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteServicio {

    @Autowired
    IDocenteRepositorio docenteRepositorio;

    @Autowired
    IUsuarioRepositorio usuarioRepositorio;

    @Autowired
    IMapaDocente mapa;

    public DocenteDTO guardarDocente(DocenteDTO datosDocente) throws Exception {
        try {
            Optional<Usuario> usuarioBuscado = usuarioRepositorio.findByCorreo(datosDocente.getCorreo());
            if (usuarioBuscado.isPresent()) {
                throw new Exception("El correo ya está registrado");
            }

            Usuario usuario = mapa.convertirDTOAUsuario(datosDocente);
            usuario.setRol(Roles.Docente);
            usuario.setEstado(Estados.Activo);
            Usuario usuarioGuardado = usuarioRepositorio.save(usuario);

            Docente docente = mapa.convertirDTOADocente(datosDocente);
            docente.setUsuario(usuarioGuardado);

            Docente docenteGuardado = docenteRepositorio.save(docente);
            return mapa.convertirEntidadADTO(docenteGuardado);

        } catch (Exception error) {
            throw new Exception("Fallamos guardando el docente: " + error.getMessage());
        }
    }

    public List<DocenteDTO> buscarTodos() throws Exception {
        try {
            return mapa.convertirListaA_DTO(docenteRepositorio.findAll());
        } catch (Exception error) {
            throw new Exception("Fallamos buscando los docentes: " + error.getMessage());
        }
    }

    public DocenteDTO buscarPorId(Integer id) throws Exception {
        try {
            Optional<Docente> docenteBuscado = docenteRepositorio.findById(id);
            if (docenteBuscado.isEmpty()) {
                throw new Exception("Docente no encontrado");
            }
            return mapa.convertirEntidadADTO(docenteBuscado.get());
        } catch (Exception error) {
            throw new Exception("Fallamos buscando el docente: " + error.getMessage());
        }
    }

    public DocenteDTO actualizarDocente(Integer id, DocenteDTO datosNuevos) throws Exception {
        try {
            Optional<Docente> docenteBuscado = docenteRepositorio.findById(id);
            if (docenteBuscado.isEmpty()) {
                throw new Exception("Docente no encontrado para actualizar");
            }

            Docente docenteExistente = docenteBuscado.get();
            Usuario usuarioExistente = docenteExistente.getUsuario();

            usuarioExistente.setNombre(datosNuevos.getNombre());
            usuarioExistente.setCorreo(datosNuevos.getCorreo());
            usuarioExistente.setContraseña(datosNuevos.getContraseña());
            usuarioRepositorio.save(usuarioExistente);

            docenteExistente.setEscalafon(datosNuevos.getEscalafon());

            Docente docenteActualizado = docenteRepositorio.save(docenteExistente);
            return mapa.convertirEntidadADTO(docenteActualizado);

        } catch (Exception error) {
            throw new Exception("Fallamos actualizando el docente: " + error.getMessage());
        }
    }

    public boolean eliminarDocente(Integer id) throws Exception {
        try {
            Optional<Docente> docenteBuscado = docenteRepositorio.findById(id);
            if (docenteBuscado.isEmpty()) {
                throw new Exception("Docente no encontrado para eliminar");
            }
            docenteRepositorio.deleteById(id);
            return true;
        } catch (Exception error) {
            throw new Exception("Fallamos eliminando el docente: " + error.getMessage());
        }
    }
}