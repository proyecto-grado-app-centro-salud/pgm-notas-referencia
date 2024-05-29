package com.example.microservicio_notas_referencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.microservicio_notas_referencia.model.NotasReferenciaEntity;
import com.example.microservicio_notas_referencia.model.dto.NotasReferenciaDto;

public interface NotasReferenciaRepository extends JpaRepository<NotasReferenciaEntity, Integer> {
    
    // @Query(value = "SELECT new com.example.microservicio_notas_referencia.model.dto.NotaReferenciaDto(nr.id_nota_referencia, hc.id_historia_clinica, p.nombres) " +
    //                "FROM notas_referencia nr " +
    //                "INNER JOIN historias_clinicas hc ON hc.id_historia_clinica = nr.id_historia_clinica " +
    //                "INNER JOIN pacientes p ON p.id_paciente = hc.id_paciente", nativeQuery = true)
    @Query("SELECT new com.example.microservicio_notas_referencia.model.dto.NotaReferenciaDto(" +
           "nr.idNotaReferencia, hc.idHistoriaClinica, p.idPaciente) " +
           "FROM NotasReferenciaEntity nr " +
           "INNER JOIN nr.historiaClinica hc " +
           "INNER JOIN hc.paciente p")
    List<NotasReferenciaDto> buscarNotasReferencia();
    // @Query("SELECT " +
    //        "nr.id_nota_referencia, hc.id_historia_clinica, p.id_paciente " +
    //        "FROM notas_referencia nr " +
    //        "INNER JOIN historias_clinicas hc ON hc.id_historia_clinica = nr.id_historia_clinica " +
    //        "INNER JOIN pacientes p ON p.id_paciente = hc.id_paciente ")
    // List<Object> buscarNotasReferencia();

    @Query("SELECT new com.example.microservicio_notas_referencia.model.dto.NotaReferenciaDto(" +
    "nr.idNotaReferencia, hc.idHistoriaClinica, p.idPaciente) " +
    "FROM NotasReferenciaEntity nr " +
    "INNER JOIN nr.historiaClinica hc " +
    "INNER JOIN hc.paciente p")
    NotasReferenciaDto buscarNotaReferenciaPorId(int id);

    @Query("SELECT new com.example.microservicio_notas_referencia.model.dto.NotaReferenciaDto(" +
    "nr.idNotaReferencia, hc.idHistoriaClinica, p.idPaciente) " +
    "FROM NotasReferenciaEntity nr " +
    "INNER JOIN nr.historiaClinica hc " +
    "INNER JOIN hc.paciente p")
    List<NotasReferenciaEntity> buscarNotasReferenciaPacientePorId(int idPaciente);
}