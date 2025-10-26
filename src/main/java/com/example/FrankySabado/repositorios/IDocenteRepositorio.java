package com.example.FrankySabado.repositorios;

import com.example.FrankySabado.modelos.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocenteRepositorio extends JpaRepository<Docente, Integer> {
}