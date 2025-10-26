package com.example.FrankySabado.modelos.dtos;

import com.example.FrankySabado.ayudas.Parentescos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FamiliarDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Debe ingresar un correo válido")
    private String correo;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener mínimo 6 caracteres")
    private String contraseña;

    @NotNull(message = "El parentesco no puede ser nulo")
    private Parentescos parentesco;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(max = 10, message = "El teléfono no puede tener más de 10 dígitos")
    private String telefono;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    public FamiliarDTO() {
    }

    public FamiliarDTO(String nombre, String correo, String contraseña, Parentescos parentesco, String telefono, String direccion) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.parentesco = parentesco;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Parentescos getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentescos parentesco) {
        this.parentesco = parentesco;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}