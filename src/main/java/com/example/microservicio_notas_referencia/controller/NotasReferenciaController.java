package com.example.microservicio_notas_referencia.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.microservicio_notas_referencia.model.NotasReferenciaEntity;
import com.example.microservicio_notas_referencia.model.dto.NotasReferenciaDto;
import com.example.microservicio_notas_referencia.repository.NotasReferenciaRepository;
import com.example.microservicio_notas_referencia.service.ContainerMetadataService;
import com.example.microservicio_notas_referencia.service.NotasReferenciaService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("notas-referencia")
public class NotasReferenciaController {
    @Autowired
    private NotasReferenciaRepository notasReferenciaRepository;

    @Autowired
    private ContainerMetadataService containerMetadataService;

    @Autowired
    private NotasReferenciaService notasReferenciaService;
    
    // @GetMapping("/{idNotaReferencia}")
    // public @ResponseBody NotasReferenciaEntity obtenerDetalleNotaReferencia(@PathVariable int idNotaReferencia) {
    //     return notasReferenciaRepository.findById(idNotaReferencia)
    //     .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error en la peticion"));
    // }
    @GetMapping("/{idNotaReferencia}")
    public NotasReferenciaDto obtenerDetalleNotaReferencia(@RequestParam int id) {
        return notasReferenciaRepository.buscarNotaReferenciaPorId(id);
    }
    
    @GetMapping()
    public @ResponseBody List<NotasReferenciaDto> obtenerTodasNotasReferencia() {
        return notasReferenciaRepository.buscarNotasReferencia();
    }
    // @GetMapping("/detalle-historia-paciente")
    // public @ResponseBody List<NotasReferenciaDto> obtenerTodasNotasReferenciaDetalleHistoriaPaciente() {
    //     return notasReferenciaRepository.buscarNotasReferencia();
    // }
    @GetMapping("/paciente/{idPaciente}")
    public @ResponseBody List<NotasReferenciaEntity> obtenerNotasReferenciaPaciente(@PathVariable int idPaciente) {
        return notasReferenciaRepository.buscarNotasReferenciaPacientePorId(idPaciente);
    }
    @PostMapping()
    public @ResponseBody String registrarNotaReferencia(@RequestBody NotasReferenciaDto nuevo){
        NotasReferenciaDto notasReferenciaDto=notasReferenciaService.guardarNotaReferencia(nuevo);
        return "Ok";        
    }
    @PutMapping("/{id}")
    public @ResponseBody String actualizarNotaReferencia(@PathVariable int id, @RequestBody NotasReferenciaDto actualizada) {
        NotasReferenciaDto notasReferenciaDto=notasReferenciaService.actualizarNotaReferencia(id,actualizada);
        return  "Ok";
    }

}
