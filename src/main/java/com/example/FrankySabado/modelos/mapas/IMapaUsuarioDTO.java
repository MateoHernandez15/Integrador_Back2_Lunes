package com.example.FrankySabado.modelos.mapas;

import com.example.FrankySabado.modelos.Usuario;
import com.example.FrankySabado.modelos.dtos.UsuarioGenericoDTO;
import com.example.FrankySabado.modelos.dtos.UsuarioRegistroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapaUsuarioDTO {

    IMapaUsuarioDTO INSTANCE= Mappers.getMapper(IMapaUsuarioDTO.class);

    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "rol", target = "rol")
    UsuarioGenericoDTO convertirADTO(Usuario usuario);

    List<UsuarioGenericoDTO> convertirListaDTO(List<Usuario> lista);

    Usuario convertirDTOAEntidad(UsuarioRegistroDTO usuarioRegistroDTO);

}