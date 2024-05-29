package com.example.microservicio_notas_referencia.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservicio_notas_referencia.model.HistoriaClinicaEntity;


public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinicaEntity, Integer> {
    List<HistoriaClinicaEntity> findByIdPaciente(int idPaciente);
}