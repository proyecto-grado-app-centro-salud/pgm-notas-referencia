package com.example.microservicio_notas_referencia.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservicio_notas_referencia.model.HistoriaClinicaEntity;
import com.example.microservicio_notas_referencia.model.MedicoEntity;
import com.example.microservicio_notas_referencia.model.NotasReferenciaEntity;
import com.example.microservicio_notas_referencia.model.dto.NotasReferenciaDto;
import com.example.microservicio_notas_referencia.repository.HistoriaClinicaRepository;
import com.example.microservicio_notas_referencia.repository.MedicoRepository;
import com.example.microservicio_notas_referencia.repository.NotasReferenciaRepository;

@Service
public class NotasReferenciaService {
    @Autowired
    HistoriaClinicaRepository historiaClinicaRepository;
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    NotasReferenciaRepository notasReferenciaRepository;


    public NotasReferenciaDto guardarNotaReferencia(NotasReferenciaDto notasReferenciaDto){
        NotasReferenciaEntity notasReferenciaEntity=new NotasReferenciaEntity();
        notasReferenciaEntity.setDatosClinicos(notasReferenciaDto.getDatosClinicos());
        notasReferenciaEntity.setDatosIngreso(notasReferenciaDto.getDatosIngreso());
        notasReferenciaEntity.setDatosEgreso(notasReferenciaDto.getDatosEgreso());
        notasReferenciaEntity.setCondicionesPacienteMomentoTransferencia(notasReferenciaDto.getCondicionesPacienteMomentoTransferencia());
        notasReferenciaEntity.setInformeProcedimientosRealizados(notasReferenciaDto.getInformeProcedimientosRealizados());
        notasReferenciaEntity.setTratamientoEfectuado(notasReferenciaDto.getTratamientoEfectuado());
        notasReferenciaEntity.setTratamientoPersistePaciente(notasReferenciaDto.getTratamientoPersistePaciente());
        notasReferenciaEntity.setFechaVencimiento(notasReferenciaDto.getFechaVencimiento());
        notasReferenciaEntity.setAdvertenciasFactoresRiesgo(notasReferenciaDto.getAdvertenciasFactoresRiesgo());
        notasReferenciaEntity.setComentarioAdicional(notasReferenciaDto.getComentarioAdicional());
        notasReferenciaEntity.setMonitoreo(notasReferenciaDto.getMonitoreo());
        notasReferenciaEntity.setInformeTrabajoSocial(notasReferenciaDto.getInformeTrabajoSocial());
        HistoriaClinicaEntity historiaClinicaEntity=historiaClinicaRepository.findById(notasReferenciaDto.getIdHistoriaClinica()).orElseThrow();
        notasReferenciaEntity.setHistoriaClinica(historiaClinicaEntity);
        MedicoEntity medicoEntity=medicoRepository.findById(notasReferenciaDto.getIdMedico()).orElseThrow();
        notasReferenciaEntity.setMedico(medicoEntity);
        NotasReferenciaEntity notaGuardada=notasReferenciaRepository.save(notasReferenciaEntity);
        return new NotasReferenciaDto(notaGuardada);
    }


    public NotasReferenciaDto actualizarNotaReferencia(int id, NotasReferenciaDto actualizada) {
        NotasReferenciaEntity notaReferencia=notasReferenciaRepository.findById(id).orElseThrow();
        notaReferencia.setDatosClinicos(actualizada.getDatosClinicos());
        notaReferencia.setDatosIngreso(actualizada.getDatosIngreso());
        notaReferencia.setDatosEgreso(actualizada.getDatosEgreso());
        notaReferencia.setCondicionesPacienteMomentoTransferencia(actualizada.getCondicionesPacienteMomentoTransferencia());
        notaReferencia.setInformeProcedimientosRealizados(actualizada.getInformeProcedimientosRealizados());
        notaReferencia.setTratamientoEfectuado(actualizada.getTratamientoEfectuado());
        notaReferencia.setTratamientoPersistePaciente(actualizada.getTratamientoPersistePaciente());
        notaReferencia.setFechaVencimiento(actualizada.getFechaVencimiento());
        notaReferencia.setAdvertenciasFactoresRiesgo(actualizada.getAdvertenciasFactoresRiesgo());
        notaReferencia.setComentarioAdicional(actualizada.getComentarioAdicional());
        notaReferencia.setMonitoreo(actualizada.getMonitoreo());
        notaReferencia.setInformeTrabajoSocial(actualizada.getInformeTrabajoSocial());
        MedicoEntity medicoEntity=medicoRepository.findById(actualizada.getIdMedico()).orElseThrow();
        notaReferencia.setMedico(medicoEntity);
        HistoriaClinicaEntity historiaClinicaEntity=historiaClinicaRepository.findById(actualizada.getIdHistoriaClinica()).orElseThrow();
        notaReferencia.setHistoriaClinica(historiaClinicaEntity);
        notaReferencia.setUpdatedAt(new Date());
        NotasReferenciaEntity nuevo = notasReferenciaRepository.save(notaReferencia);
        return new NotasReferenciaDto(nuevo);
    }
}
