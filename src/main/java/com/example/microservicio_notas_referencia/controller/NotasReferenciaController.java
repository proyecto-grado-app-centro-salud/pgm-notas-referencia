package com.example.microservicio_notas_referencia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.microservicio_notas_referencia.model.NotasReferenciaEntity;
import com.example.microservicio_notas_referencia.repository.NotasReferenciaRepository;
import com.example.microservicio_notas_referencia.service.ContainerMetadataService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotasReferenciaController {
    @Autowired
    private NotasReferenciaRepository notasReferenciaRepository;

    @Autowired
    private ContainerMetadataService containerMetadataService;
    
    @GetMapping("/{idNotaReferencia}")
    public @ResponseBody NotasReferenciaEntity obtenerDetalleNotaReferencia(@PathVariable int idNotaReferencia) {
        return notasReferenciaRepository.findById(idNotaReferencia)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error en la peticion"));
    }

}
