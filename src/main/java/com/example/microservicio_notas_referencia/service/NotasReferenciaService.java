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
        notasReferenciaEntity.setDatosClinicos(notasReferenciaEntity.getDatosClinicos());
        notasReferenciaEntity.setDatosIngreso(notasReferenciaEntity.getDatosIngreso());
        notasReferenciaEntity.setDatosEgreso(notasReferenciaEntity.getDatosEgreso());
        notasReferenciaEntity.setCondicionesPacienteMomentoTransferencia(notasReferenciaEntity.getCondicionesPacienteMomentoTransferencia());
        notasReferenciaEntity.setInformeProcedimientosRealizados(notasReferenciaEntity.getInformeProcedimientosRealizados());
        notasReferenciaEntity.setTratamientoEfectuado(notasReferenciaEntity.getTratamientoEfectuado());
        notasReferenciaEntity.setTratamientoPersistePaciente(notasReferenciaEntity.getTratamientoPersistePaciente());
        notasReferenciaEntity.setFechaVencimiento(notasReferenciaEntity.getFechaVencimiento());
        notasReferenciaEntity.setAdvertenciasFactoresRiesgo(notasReferenciaEntity.getAdvertenciasFactoresRiesgo());
        notasReferenciaEntity.setComentarioAdicional(notasReferenciaEntity.getComentarioAdicional());
        notasReferenciaEntity.setMonitoreo(notasReferenciaEntity.getMonitoreo());
        notasReferenciaEntity.setInformeTrabajoSocial(notasReferenciaEntity.getInformeTrabajoSocial());
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
        notaReferencia.setUpdatedAt(new Date());
        NotasReferenciaEntity nuevo = notasReferenciaRepository.save(notaReferencia);
        return new NotasReferenciaDto(nuevo);
    }
}
