package com.oscarCrud.GA7.repositorio;

import com.oscarCrud.GA7.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepositorio extends JpaRepository<Empleado, Integer> {
}
