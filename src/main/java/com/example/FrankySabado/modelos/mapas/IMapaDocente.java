package com.example.FrankySabado.modelos.mapas;

import com.example.FrankySabado.modelos.Docente;
import com.example.FrankySabado.modelos.Usuario;
import com.example.FrankySabado.modelos.dtos.DocenteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapaDocente {

    IMapaDocente INSTANCE = Mappers.getMapper(IMapaDocente.class);

    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "contraseña", target = "contraseña")
    Usuario convertirDTOAUsuario(DocenteDTO dto);

    @Mapping(source = "escalafon", target = "escalafon")
    Docente convertirDTOADocente(DocenteDTO dto);

    @Mapping(source = "usuario.nombre", target = "nombre")
    @Mapping(source = "usuario.correo", target = "correo")
    @Mapping(target = "contraseña", ignore = true)
    @Mapping(source = "escalafon", target = "escalafon")
    DocenteDTO convertirEntidadADTO(Docente docente);

    List<DocenteDTO> convertirListaA_DTO(List<Docente> docentes);
}