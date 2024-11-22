package com.example.microservicio_notas_referencia.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.microservicio_notas_referencia.model.EspecialidadesEntity;
import com.example.microservicio_notas_referencia.model.HistoriaClinicaEntity;
import com.example.microservicio_notas_referencia.model.NotasReferenciaEntity;
import com.example.microservicio_notas_referencia.model.UsuarioEntity;
import com.example.microservicio_notas_referencia.model.dto.NotasReferenciaDto;
import com.example.microservicio_notas_referencia.repository.EspecialidadesRepositoryJPA;
import com.example.microservicio_notas_referencia.repository.HistoriaClinicaRepository;
import com.example.microservicio_notas_referencia.repository.NotasReferenciaRepository;
import com.example.microservicio_notas_referencia.repository.UsuariosRepositoryJPA;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class PDFService {
    @Autowired
    com.example.microservicio_notas_referencia.repository.UsuariosRepositoryJPA usuariosRepositoryJPA;
    @Autowired
    HistoriaClinicaRepository historiaClinicaRepositoryJPA;
    @Autowired
    EspecialidadesRepositoryJPA especialidadesRepositoryJPA;
    @Autowired
    NotasReferenciaRepository notasReferenciaRepository;

    public byte[] generarPdfReporteNotaReferencia(NotasReferenciaDto notaReferenciaDto) throws JRException{
        Optional<NotasReferenciaEntity> notaEvolucionEntityOptional=(notaReferenciaDto.getId()!=null)?notasReferenciaRepository.findById(notaReferenciaDto.getId()):Optional.empty();
        if(notaEvolucionEntityOptional.isPresent()){
            notaReferenciaDto=new NotasReferenciaDto(notaEvolucionEntityOptional.get());
        }else{
            notaReferenciaDto.setCreatedAt(new Date());
            notaReferenciaDto.setUpdatedAt(new Date());
        }
        InputStream jrxmlInputStream = getClass().getClassLoader().getResourceAsStream("reports/nota_referencia.jrxml");
        HistoriaClinicaEntity historiaClinicaEntity = historiaClinicaRepositoryJPA.findById(notaReferenciaDto.getIdHistoriaClinica()).orElseThrow(() -> new RuntimeException("Historia clinica no encontrada"));
        UsuarioEntity pacienteEntity = usuariosRepositoryJPA.findById(historiaClinicaEntity.getPaciente().getIdUsuario()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        UsuarioEntity medicoEntity = usuariosRepositoryJPA.findById(notaReferenciaDto.getIdMedico()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        EspecialidadesEntity especialidadesEntity = especialidadesRepositoryJPA.findById(historiaClinicaEntity.getEspecialidad().getIdEspecialidad()).orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
        if (jrxmlInputStream == null) {
            throw new JRException("No se pudo encontrar el archivo .jrxml en el classpath.");
        }
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlInputStream);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("datosClinicos",notaReferenciaDto.getDatosClinicos());
        parameters.put("datosDeIngreso",notaReferenciaDto.getDatosIngreso());
        parameters.put("datosDeEgreso",notaReferenciaDto.getDatosEgreso());
        parameters.put("condicionesDelPaciente",notaReferenciaDto.getCondicionesPacienteMomentoTransferencia());
        parameters.put("informeDeProcedimientos",notaReferenciaDto.getInformeProcedimientosRealizados() );
        parameters.put("tratamientoEfectuado",notaReferenciaDto.getTratamientoEfectuado());
        parameters.put("tratamiento",notaReferenciaDto.getTratamientoPersistePaciente() );
        parameters.put("advertenciasSobreFactores",notaReferenciaDto.getAdvertenciasFactoresRiesgo());
        parameters.put("comentarioAdicional",notaReferenciaDto.getComentarioAdicional());
        parameters.put("monitoreo",notaReferenciaDto.getMonitoreo());
        parameters.put("informeTrabajoSocial",notaReferenciaDto.getInformeTrabajoSocial() );

        parameters.put("apellidoPaterno", pacienteEntity.getApellidoPaterno());
        parameters.put("apellidoMaterno", pacienteEntity.getApellidoMaterno());
        parameters.put("nombres", pacienteEntity.getNombres());
        parameters.put("nhc", historiaClinicaEntity.getIdHistoriaClinica()+"");
        parameters.put("edad", pacienteEntity.getEdad()+"");
        parameters.put("sexo", pacienteEntity.getSexo());
        parameters.put("estadoCivil", pacienteEntity.getEstadoCivil());
        parameters.put("unidad", especialidadesEntity.getNombre());



        parameters.put("fecha", formato.format(notaReferenciaDto.getUpdatedAt()));
        parameters.put("nombreCompletoPaciente", pacienteEntity.getNombres()+" "+pacienteEntity.getApellidoPaterno());
        parameters.put("nombreCompletoMedico", medicoEntity.getNombres()+" "+medicoEntity.getApellidoPaterno());
        parameters.put("firmaPaciente", "");
        parameters.put("firmaMedico", "");

        parameters.put("IMAGE_PATH", getClass().getClassLoader().getResource("images/logo.jpeg").getPath());
        List<NotasReferenciaDto> list = new ArrayList<>();
        list.add(notaReferenciaDto);

        JRBeanCollectionDataSource emptyDataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, emptyDataSource);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

       return outputStream.toByteArray();
    }
}
