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
    private int idNotaReferencia;
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
    private int idPropietario;
    private int idMedico;
    private Date createdAt;
    private Date updatedAt;
    public NotasReferenciaDto(Integer idNotaReferencia, String datosClinicos, String datosIngreso, String datosEgreso,
            String condicionesPacienteMomentoTransferencia, String informeProcedimientosRealizados,
            String tratamientoEfectuado, String tratamientoPersistePaciente, Date fechaVencimiento,
            String advertenciasFactoresRiesgo, String comentarioAdicional, String monitoreo,
            String informeTrabajoSocial,int idHistoriaClinica,String ciPropietario,String nombres,int idPaciente,int idMedico,
            Date createdAt,Date updatedAt) {
        this.idNotaReferencia = idNotaReferencia;
        this.datosClinicos = datosClinicos;
        this.datosIngreso = datosIngreso;
        this.datosEgreso = datosEgreso;
        this.condicionesPacienteMomentoTransferencia = condicionesPacienteMomentoTransferencia;
        this.informeProcedimientosRealizados = informeProcedimientosRealizados;
        this.tratamientoEfectuado = tratamientoEfectuado;
        this.tratamientoPersistePaciente = tratamientoPersistePaciente;
        this.fechaVencimiento = fechaVencimiento;
        this.advertenciasFactoresRiesgo = advertenciasFactoresRiesgo;
        this.comentarioAdicional = comentarioAdicional;
        this.monitoreo = monitoreo;
        this.informeTrabajoSocial = informeTrabajoSocial;
        this.idHistoriaClinica=idHistoriaClinica;
        this.ciPropietario=ciPropietario;
        this.pacientePropietario=nombres;
        this.idPropietario=idPaciente;
        this.idMedico=idMedico;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }
    
}
