package com.example.microservicio_notas_referencia.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "notas_referencia")
public class NotasReferenciaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota_referencia")
    private int idNotaReferencia;

    @Column(name = "datos_clinicos")
    private String datosClinicos;

    @Column(name = "datos_ingreso")
    private String datosIngreso;

    @Column(name = "datos_egreso")
    private String datosEgreso;

    @Column(name = "condiciones_paciente_momento_transferencia")
    private String condicionesPacienteMomentoTransferencia;

    @Column(name = "informe_procedimientos_realizados")
    private String informeProcedimientosRealizados;

    @Column(name = "tratamiento_efectuado")
    private String tratamientoEfectuado;

    @Column(name = "tratamiento_persiste_paciente")
    private String tratamientoPersistePaciente;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;

    @Column(name = "advertencias_factores_riesgo")
    private String advertenciasFactoresRiesgo;

    @Column(name = "comentario_adicional")
    private String comentarioAdicional;

    @Column(name = "monitoreo")
    private String monitoreo;

    @Column(name = "informe_trabajo_social")
    private String informeTrabajoSocial;

    // @Column(name = "id_historia_clinica")
    // private String idHistoriaClinica;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_historia_clinica", nullable = false)
    private HistoriaClinicaEntity historiaClinica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico", nullable = false)
    private UsuarioEntity medico;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    private Date deletedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public void markAsDeleted() {
        deletedAt = new Date();
    }
}
