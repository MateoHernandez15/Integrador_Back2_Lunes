package com.example.FrankySabado.modelos;

import com.example.FrankySabado.ayudas.SectorEmpresa;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "empresario")
public class Empresario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "sector", nullable = false)
    @Enumerated(EnumType.STRING)
    private SectorEmpresa sector;

    @Column(name = "departamento", nullable = false)
    private String departamento;

    @OneToOne(mappedBy = "empresario")
    @JsonBackReference
    private Usuario usuario;

    public Empresario() {
    }

    public Empresario(Integer id, String nombre, SectorEmpresa sector, String departamento) {
        this.id = id;
        this.nombre = nombre;
        this.sector = sector;
        this.departamento = departamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SectorEmpresa getSector() {
        return sector;
    }

    public void setSector(SectorEmpresa sector) {
        this.sector = sector;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
