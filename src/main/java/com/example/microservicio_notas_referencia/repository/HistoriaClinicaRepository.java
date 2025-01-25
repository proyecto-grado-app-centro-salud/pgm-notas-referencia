package com.example.microservicio_notas_referencia.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservicio_notas_referencia.model.HistoriaClinicaEntity;
import com.example.microservicio_notas_referencia.model.UsuarioEntity;


public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinicaEntity, Integer> {

    Optional<HistoriaClinicaEntity> findByIdHistoriaClinicaAndDeletedAtIsNull(int idHistoriaClinica);
}