package com.example.FrankySabado.repositorios;

import com.example.FrankySabado.modelos.Familiar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFamiliarRepositorio extends JpaRepository<Familiar, Integer> {
}