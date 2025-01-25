package com.example.microservicio_notas_referencia.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import jakarta.annotation.security.PermitAll;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

     // @GetMapping("/detalle-historia-paciente")
    // public @ResponseBody List<NotasReferenciaDto> obtenerTodasNotasReferenciaDetalleHistoriaPaciente() {
    //     return notasReferenciaRepository.buscarNotasReferencia();
    // }

    
    @GetMapping("/{idNotaReferencia}")
    @PermitAll
    public @ResponseBody NotasReferenciaDto obtenerDetalleNotaReferencia(@PathVariable int idNotaReferencia) {
        return notasReferenciaService.buscarNotaReferenciaPorId(idNotaReferencia);
    }
    @GetMapping("/paciente/{idPaciente}")
    @PermitAll
    public @ResponseBody Page<NotasReferenciaDto> obtenerNotasReferenciaPaciente(@PathVariable int idPaciente,@RequestParam(required = false) String fechaInicio, @RequestParam(required = false) String fechaFin,@RequestParam(required = false) String nombreMedico,@RequestParam(required = false) String nombreEspecialidad,@RequestParam(required = false) String diagnosticoPresuntivo,@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer size) {
        return notasReferenciaService.buscarNotasReferenciaPacientePorId(idPaciente,fechaInicio,fechaFin,nombreMedico,nombreEspecialidad,diagnosticoPresuntivo,page,size);
    }
    @GetMapping()
    @PermitAll
    public @ResponseBody Page<NotasReferenciaDto> obtenerTodasNotasReferencia(@RequestParam(required = false) String fechaInicio, @RequestParam(required = false) String fechaFin,@RequestParam(required = false) String ciPaciente,@RequestParam(required = false) String nombrePaciente,@RequestParam(required = false) String nombreMedico,@RequestParam(required = false) String nombreEspecialidad,@RequestParam(required = false) String diagnosticoPresuntivo,@RequestParam(required = false) Integer page,@RequestParam(required = false) Integer size) {
        return notasReferenciaService.buscarNotasReferencia(fechaInicio,fechaFin,ciPaciente,nombrePaciente,nombreMedico,nombreEspecialidad,diagnosticoPresuntivo,page,size);
    }
   

    @PostMapping()
    @PermitAll
    public @ResponseBody NotasReferenciaDto registrarNotaReferencia(@RequestBody NotasReferenciaDto nuevo){
        NotasReferenciaDto notasReferenciaDto=notasReferenciaService.guardarNotaReferencia(nuevo);
        return notasReferenciaDto;        
    }
    @PutMapping("/{id}")
    @PermitAll
    public @ResponseBody NotasReferenciaDto actualizarNotaReferencia(@PathVariable int id, @RequestBody NotasReferenciaDto actualizada) {
        NotasReferenciaDto notasReferenciaDto=notasReferenciaService.actualizarNotaReferencia(id,actualizada);
        return  notasReferenciaDto;
    }
    @GetMapping("/pdf")
    @PermitAll
    public ResponseEntity<byte[]> obtenerPDFDeNotaReferencia(NotasReferenciaDto notaReferenciaDto) {
        try {
            byte[] pdfBytes = notasReferenciaService.obtenerPDFNotaReferencia(notaReferenciaDto);
            
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=NotaEvolucion.pdf");
            headers.add("Content-Type", "application/pdf");
            headers.add("Content-Length", "" + pdfBytes.length);

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping(value = "/{id}")
    @PermitAll
    public ResponseEntity<Void> delete(@PathVariable int id) {
        try{
            notasReferenciaService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/historia-clinica/{id}")
    @PermitAll
    public ResponseEntity<Void> deleteNotasReferenciaDeHistoriaClinica(@PathVariable int id) {
        try{
            notasReferenciaService.deleteNotasReferenciaDeHistoriaClinica(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
