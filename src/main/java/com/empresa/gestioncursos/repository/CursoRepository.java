package com.empresa.gestioncursos.repository;

import com.empresa.gestioncursos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso,Integer> {
}
