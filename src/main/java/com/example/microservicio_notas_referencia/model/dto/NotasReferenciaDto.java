package com.example.microservicio_notas_referencia.model.dto;
import java.util.Date;

import com.example.microservicio_notas_referencia.model.NotasReferenciaEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotasReferenciaDto {
    public NotasReferenciaDto(NotasReferenciaEntity notaGuardada) {
        this.datosClinicos=notaGuardada.getDatosClinicos();
        this.datosIngreso=notaGuardada.getDatosIngreso();
        this.datosEgreso=notaGuardada.getDatosEgreso();
        this.condicionesPacienteMomentoTransferencia=notaGuardada.getCondicionesPacienteMomentoTransferencia();
        this.informeProcedimientosRealizados=notaGuardada.getInformeProcedimientosRealizados();
        this.tratamientoEfectuado=notaGuardada.getTratamientoEfectuado();
        this.tratamientoPersistePaciente=notaGuardada.getTratamientoPersistePaciente();
        this.fechaVencimiento=notaGuardada.getFechaVencimiento();
        this.advertenciasFactoresRiesgo=notaGuardada.getAdvertenciasFactoresRiesgo();
        this.comentarioAdicional=notaGuardada.getComentarioAdicional();
        this.monitoreo=notaGuardada.getMonitoreo();
        this.informeTrabajoSocial=notaGuardada.getInformeTrabajoSocial();
        this.idHistoriaClinica=notaGuardada.getHistoriaClinica().getIdHistoriaClinica();
        this.idMedico=notaGuardada.getMedico().getIdMedico();
    }
    private String datosClinicos;
    private String datosIngreso;
    private String datosEgreso;
    private String condicionesPacienteMomentoTransferencia;
    private String informeProcedimientosRealizados;
    private String tratamientoEfectuado;
    private String tratamientoPersistePaciente;
    private Date fechaVencimiento;
    private String advertenciasFactoresRiesgo;
    private String comentarioAdicional;
    private String monitoreo;
    private String informeTrabajoSocial;
    private int idHistoriaClinica;
    private String ciPropietario;
    private String pacientePropietario;
    private int idMedico;
    
}
