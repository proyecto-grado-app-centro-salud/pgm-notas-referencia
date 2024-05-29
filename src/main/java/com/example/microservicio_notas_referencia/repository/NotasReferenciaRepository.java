package com.example.microservicio_notas_referencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservicio_notas_referencia.model.NotasReferenciaEntity;

public interface NotasReferenciaRepository extends JpaRepository<NotasReferenciaEntity, Integer> {
}