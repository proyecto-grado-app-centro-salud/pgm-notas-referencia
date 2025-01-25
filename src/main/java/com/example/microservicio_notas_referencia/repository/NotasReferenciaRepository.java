package com.example.microservicio_notas_referencia.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.microservicio_notas_referencia.model.NotasReferenciaEntity;
import com.example.microservicio_notas_referencia.model.dto.NotasReferenciaDto;

public interface NotasReferenciaRepository extends JpaRepository<NotasReferenciaEntity, Integer>,JpaSpecificationExecutor<NotasReferenciaEntity> {
    
    // @Query(value = "SELECT new com.example.microservicio_notas_referencia.model.dto.NotaReferenciaDto(nr.id_nota_referencia, hc.id_historia_clinica, p.nombres) " +
    //                "FROM notas_referencia nr " +
    //                "INNER JOIN historias_clinicas hc ON hc.id_historia_clinica = nr.id_historia_clinica " +
    //                "INNER JOIN pacientes p ON p.id_paciente = hc.id_paciente", nativeQuery = true)
    @Query("SELECT new com.example.microservicio_notas_referencia.model.dto.NotasReferenciaDto(nr)" +
           "FROM NotasReferenciaEntity nr " +
           "INNER JOIN nr.historiaClinica hc " +
           "INNER JOIN hc.paciente p")
    List<NotasReferenciaDto> buscarNotasReferencia();
    // @Query("SELECT " +
    //        "nr.id_nota_referencia, hc.id_historia_clinica, p.id_paciente " +
    //        "FROM notas_referencia nr " +
    //        "INNER JOIN historias_clinicas hc ON hc.id_historia_clinica = nr.id_historia_clinica " +
    //        "INNER JOIN pacientes p ON p.id_paciente = hc.id_paciente ")
    // List<Object> buscarNotasReferencia();z

    @Query("SELECT new com.example.microservicio_notas_referencia.model.dto.NotasReferenciaDto(nr) " +
    "FROM NotasReferenciaEntity nr " +
    "INNER JOIN nr.historiaClinica hc " +
    "INNER JOIN hc.paciente p "+
    "WHERE nr.idNotaReferencia = ?1")
    NotasReferenciaDto buscarNotaReferenciaPorId(int id);

    @Query("SELECT new com.example.microservicio_notas_referencia.model.dto.NotasReferenciaDto(nr) " +
    "FROM NotasReferenciaEntity nr " +
    "INNER JOIN nr.historiaClinica hc " +
    "INNER JOIN hc.paciente p "+
    "WHERE p.idUsuario = ?1")
    List<NotasReferenciaDto> buscarNotasReferenciaPacientePorId(int idPaciente);

    Optional<NotasReferenciaEntity> findByIdNotaReferenciaAndDeletedAtIsNull(int idNotaReferencia);

    @Modifying
    @Query(value = "UPDATE notas_referencia SET deleted_at = ?2 WHERE id_historia_clinica = ?1", nativeQuery = true)
    void markAsDeletedAllNotasReferenciaFromHistoriaClinica(int idHistoriaClinica, Date date);
}