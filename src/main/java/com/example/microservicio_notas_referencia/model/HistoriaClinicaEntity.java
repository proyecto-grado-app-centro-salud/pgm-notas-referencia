package com.example.microservicio_notas_referencia.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "historias_clinicas")
public class HistoriaClinicaEntity {
    @Id
    @Column(name = "id_historia_clinica")
    private int idHistoriaClinica;
    @Column(name = "condiciones_actuales_estado_salud_enfermedad")
    private String amnesis;
    @Column(name = "antecedentes_familiares")
    private String antecedentesFamiliares;
    @Column(name = "antecedentes_ginecoobstetricos")
    private String antecedentesGinecoobstetricos;
    @Column(name = "antecedentes_no_patologicos")
    private String antecedentesNoPatologicos;
    @Column(name = "antecedentes_patologicos")
    private String antecedentesPatologicos;
    @Column(name = "antecedentes_personales")
    private String antecedentesPersonales;
    @Column(name = "diagnostico_presuntivo")
    private String diagnosticoPresuntivo;
    @Column(name = "diagnosticos_diferenciales")
    private String diagnosticosDiferenciales;
    @Column(name = "examen_fisico_general")
    private String examenFisico;
    @Column(name = "examen_fisico_especial")
    private String examenFisicoEspecial;
    @Column(name = "propuesta_basica_de_conducta")
    private String propuestaBasicaDeConducta;
    @Column(name = "tratamiento")
    private String tratamiento;
    // @Column(name = "id_paciente",insertable=false, updatable=false)
    // private String idPaciente;
    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "id_paciente")
    // private PacienteEntity paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false)
    private UsuarioEntity paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico", nullable = false)
    private UsuarioEntity medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especialidad", nullable = false)
    private EspecialidadesEntity especialidad;
    // @Column(name = "id_paciente")
    // private int idPaciente;
    @OneToMany(mappedBy = "historiaClinica", fetch = FetchType.LAZY)
    private List<NotasReferenciaEntity> referencias;


}
