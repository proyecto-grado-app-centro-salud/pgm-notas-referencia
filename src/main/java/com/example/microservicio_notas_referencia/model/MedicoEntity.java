// package com.example.microservicio_notas_referencia.model;

// import java.util.List;

// import io.micrometer.common.lang.Nullable;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;
// @Entity
// @Getter @Setter
// @NoArgsConstructor
// @Table(name = "medicos")
// public class MedicoEntity {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "id_medico")
//     private int idMedico;

//     @Column(name = "nombre")
//     private String nombres;

//     @Column(name = "apellido_paterno")
//     private String apellidoPaterno;

//     @Column(name = "apellido_materno")
//     private String apellidoMaterno;

//     @Column(name = "ci")
//     private String ci;

//     @Column(name = "direccion")
//     private String direccion;

//     @Column(name = "celular")
//     private String celular;

//     @Column(name = "email")
//     private String email;

//     @Column(name = "años_experiencia")
//     private Integer añosExperiencia;

//     @Column(name = "salario")
//     private Float salario;

//     @Column(name = "foto")
//     private String foto;

//     @Column(name = "descripcion")
//     private String descripcion;

//     @Column(name = "grupo_sanguineo")
//     private String grupoSanguineo;

//     @OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
//     private List<NotasReferenciaEntity> NotasReferenciaEntity;
// }
