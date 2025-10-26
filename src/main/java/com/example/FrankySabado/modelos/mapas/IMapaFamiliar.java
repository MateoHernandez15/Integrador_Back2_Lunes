package com.example.FrankySabado.modelos.mapas;

import com.example.FrankySabado.modelos.Familiar;
import com.example.FrankySabado.modelos.Usuario;
import com.example.FrankySabado.modelos.dtos.FamiliarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMapaFamiliar {

    IMapaFamiliar INSTANCE = Mappers.getMapper(IMapaFamiliar.class);

    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "contraseña", target = "contraseña")
    Usuario convertirDTOAUsuario(FamiliarDTO dto);

    @Mapping(source = "parentesco", target = "parentesco")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "direccion", target = "direccion")
    Familiar convertirDTOAFamiliar(FamiliarDTO dto);

    @Mapping(source = "usuario.nombre", target = "nombre")
    @Mapping(source = "usuario.correo", target = "correo")
    @Mapping(target = "contraseña", ignore = true)
    @Mapping(source = "parentesco", target = "parentesco")
    @Mapping(source = "telefono", target = "telefono")
    @Mapping(source = "direccion", target = "direccion")
    FamiliarDTO convertirEntidadADTO(Familiar familiar);

    List<FamiliarDTO> convertirListaA_DTO(List<Familiar> familiares);
}