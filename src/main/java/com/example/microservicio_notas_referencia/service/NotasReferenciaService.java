package com.example.microservicio_notas_referencia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.microservicio_notas_referencia.model.HistoriaClinicaEntity;
import com.example.microservicio_notas_referencia.model.NotasReferenciaEntity;
import com.example.microservicio_notas_referencia.model.UsuarioEntity;
import com.example.microservicio_notas_referencia.model.dto.NotasReferenciaDto;
import com.example.microservicio_notas_referencia.repository.HistoriaClinicaRepository;
import com.example.microservicio_notas_referencia.repository.NotasReferenciaRepository;
import com.example.microservicio_notas_referencia.repository.UsuariosRepositoryJPA;
import com.example.microservicio_notas_referencia.util.NotasReferenciaSpecification;

@Service
public class NotasReferenciaService {
    @Autowired
    HistoriaClinicaRepository historiaClinicaRepository;
    @Autowired
    UsuariosRepositoryJPA usuarioRepositoryJPA;
    @Autowired
    NotasReferenciaRepository notasReferenciaRepository;
    @Autowired
    PDFService pdfService;
    @Autowired
    private ConvertirTiposDatosService convertirTiposDatosService;
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
        UsuarioEntity medicoEntity=usuarioRepositoryJPA.findById(notasReferenciaDto.getIdMedico()).orElseThrow();
        notasReferenciaEntity.setMedico(medicoEntity);
        NotasReferenciaEntity notaGuardada=notasReferenciaRepository.save(notasReferenciaEntity);
        return new NotasReferenciaDto(notaGuardada);
    }


    public NotasReferenciaDto actualizarNotaReferencia(int id, NotasReferenciaDto actualizada) {
        NotasReferenciaEntity notaReferencia=notasReferenciaRepository.findByIdNotaReferenciaAndDeletedAtIsNull(id).orElseThrow();
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
        UsuarioEntity medicoEntity=usuarioRepositoryJPA.findById(actualizada.getIdMedico()).orElseThrow();
        notaReferencia.setMedico(medicoEntity);
        HistoriaClinicaEntity historiaClinicaEntity=historiaClinicaRepository.findById(actualizada.getIdHistoriaClinica()).orElseThrow();
        notaReferencia.setHistoriaClinica(historiaClinicaEntity);
        notaReferencia.setUpdatedAt(new Date());
        NotasReferenciaEntity nuevo = notasReferenciaRepository.save(notaReferencia);
        return new NotasReferenciaDto(nuevo);
    }


    public byte[] obtenerPDFNotaReferencia(NotasReferenciaDto notaReferenciaDto) {
        try {
            return pdfService.generarPdfReporteNotaReferencia(notaReferenciaDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar el PDF de la historia clinica.", e);
        }
    }


    public Page<NotasReferenciaDto> buscarNotasReferencia(String fechaInicio, String fechaFin, String ciPaciente,
            String nombrePaciente, String nombreMedico, String nombreEspecialidad, String diagnosticoPresuntivo,
            Integer page, Integer size) {
                
                Pageable pageable = Pageable.unpaged();
                if(page!=null && size!=null){
                    pageable = PageRequest.of(page, size);
                } 
                        Specification<NotasReferenciaEntity> spec = Specification.where(NotasReferenciaSpecification.obtenerNotasReferenciaPorParametros(convertirTiposDatosService.convertirStringADate(fechaInicio),convertirTiposDatosService.convertirStringADate(fechaFin),ciPaciente,nombrePaciente,nombreMedico,nombreEspecialidad,diagnosticoPresuntivo));
                        Page<NotasReferenciaEntity> notasEntitiesPage=notasReferenciaRepository.findAll(spec,pageable);

        
        return notasEntitiesPage.map(NotasReferenciaDto::convetirNotasReferenciaEntityNotasReferenciaDto);
        }


    public Page<NotasReferenciaDto> buscarNotasReferenciaPacientePorId(int idPaciente, String fechaInicio,
            String fechaFin, String nombreMedico, String nombreEspecialidad, String diagnosticoPresuntivo, Integer page,
            Integer size) {
                Pageable pageable = Pageable.unpaged();
                if(page!=null && size!=null){
                    pageable = PageRequest.of(page, size);
                } 
                Specification<NotasReferenciaEntity> spec = Specification.where(NotasReferenciaSpecification.obtenerNotasReferenciaDePacientePorParametros(idPaciente,convertirTiposDatosService.convertirStringADate(fechaInicio),convertirTiposDatosService.convertirStringADate(fechaFin),nombreMedico,nombreEspecialidad,diagnosticoPresuntivo));
                Page<NotasReferenciaEntity> notasEntitiesPage=notasReferenciaRepository.findAll(spec,pageable);

        
        return notasEntitiesPage.map(NotasReferenciaDto::convetirNotasReferenciaEntityNotasReferenciaDto);
            }


    public void delete(int id) {
        NotasReferenciaEntity notaReferencia=notasReferenciaRepository.findByIdNotaReferenciaAndDeletedAtIsNull(id).orElseThrow();
        notaReferencia.markAsDeleted();
        notasReferenciaRepository.save(notaReferencia);

    }


    public NotasReferenciaDto buscarNotaReferenciaPorId(int idNotaReferencia) {
        NotasReferenciaEntity notaReferencia=notasReferenciaRepository.findByIdNotaReferenciaAndDeletedAtIsNull(idNotaReferencia).orElseThrow();
        return new NotasReferenciaDto().convetirNotasReferenciaEntityNotasReferenciaDto(notaReferencia);
    }
    
}
