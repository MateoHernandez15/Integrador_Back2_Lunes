package com.example.FrankySabado.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FrankySabado.modelos.Usuario;
import com.example.FrankySabado.modelos.dtos.UsuarioGenericoDTO;
import com.example.FrankySabado.modelos.dtos.UsuarioRegistroDTO;
import com.example.FrankySabado.modelos.mapas.IMapaUsuarioDTO;
import com.example.FrankySabado.repositorios.IUsuarioRepositorio;

@Service
public class UsuarioServicio {

    @Autowired
    IUsuarioRepositorio repositorio;

    @Autowired
    IMapaUsuarioDTO mapa;

    public UsuarioGenericoDTO guardarUsuario(UsuarioRegistroDTO datosUsuarioDTO)throws Exception{
        try{
            Usuario usuario = this.mapa.convertirDTOAEntidad(datosUsuarioDTO);
            return this.mapa.convertirADTO(this.repositorio.save(usuario));

        }catch(Exception error){
            throw new Exception("A ocurrido un error "+error.getMessage());
        }
    }

    public List<UsuarioGenericoDTO> buscarUsuarios()throws Exception{
        try{
            return this.mapa.convertirListaDTO(this.repositorio.findAll());
        }catch(Exception error){
            throw new Exception("A ocurrido un error "+error.getMessage());
        }
    }

    public UsuarioGenericoDTO buscarUsuarioPorId(Integer id)throws Exception{
        try{
            Optional<Usuario>usuarioBuscado=this.repositorio.findById(id);
            if(usuarioBuscado.isPresent()){
                return this.mapa.convertirADTO(usuarioBuscado.get());
            }else{
                throw new Exception("Usuario no encontrado");
            }
        }catch(Exception error){
            throw new Exception("A ocurrido un error "+error.getMessage());
        }
    }

    public UsuarioGenericoDTO buscarUsuarioPorCorreo(String correo)throws Exception{
        try{
            Optional<Usuario>usuarioBuscado=this.repositorio.findByCorreo(correo);
            if(usuarioBuscado.isPresent()){
                return this.mapa.convertirADTO(usuarioBuscado.get());
            }else{
                throw new Exception("Usuario no encontrado");
            }
        }catch(Exception error){
            throw new Exception("A ocurrido un error "+error.getMessage());
        }
    }

}